package com.foodfrenzy.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.foodfrenzy.client.CartClient;
import com.foodfrenzy.client.NotificationClient;
import com.foodfrenzy.client.OrderClient;
import com.foodfrenzy.dto.NotificationRequest;
import com.foodfrenzy.dto.PaymentRequest;
import com.foodfrenzy.dto.PaymentResponse;
import com.foodfrenzy.entity.Payment;
import com.foodfrenzy.entity.PaymentStatus;
import com.foodfrenzy.exception.ResourceNotFoundException;
import com.foodfrenzy.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final OrderClient orderClient;
    private final NotificationClient notificationClient;
    private final CartClient cartClient;

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {

        Payment payment = modelMapper.map(request, Payment.class);

        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentDate(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        return modelMapper.map(savedPayment, PaymentResponse.class);
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment not found with id: " + id));

        return modelMapper.map(payment, PaymentResponse.class);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(payment ->
                        modelMapper.map(payment, PaymentResponse.class))
                .toList();
    }

    @Override
    public List<PaymentResponse> getPaymentsByUserId(Long userId) {

        return paymentRepository.findByUserId(userId)
                .stream()
                .map(payment ->
                        modelMapper.map(payment, PaymentResponse.class))
                .toList();
    }

    @Override
    public PaymentResponse getPaymentByOrderId(Long orderId) {

        Payment payment = paymentRepository.findByOrderId(orderId)
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment not found for order id: " + orderId));

        return modelMapper.map(payment, PaymentResponse.class);
    }

    @Override
    public PaymentResponse updatePaymentStatus(
            Long id, String status) {
    	    Payment payment = paymentRepository.findById(id)
    	            .orElseThrow(() ->
    	                    new ResourceNotFoundException(
    	                            "Payment not found with id: " + id));

    	    PaymentStatus paymentStatus=PaymentStatus.valueOf(status.toUpperCase());
    	    payment.setPaymentStatus(paymentStatus);

    	    Payment savedPayment = paymentRepository.save(payment);

    	    // If payment is successful, confirm the order
    	    if (paymentStatus == PaymentStatus.SUCCESS) {

    	        // Confirm the order
    	        orderClient.updateOrderStatus(
    	                payment.getOrderId(),
    	                "CONFIRMED"
    	        );
    	        
    	        cartClient.clearCart(payment.getUserId());

    	        // Send payment success notification
    	        NotificationRequest notificationRequest =
    	                new NotificationRequest(
    	                        payment.getUserId(),
    	                        payment.getOrderId(),
    	                        "Payment successful for your order!",
    	                        "PAYMENT_SUCCESS"
    	                );

    	        notificationClient.sendNotification(notificationRequest);
    	    }
    	    return modelMapper.map(savedPayment, PaymentResponse.class);
    	
    }

    @Override
    public PaymentResponse refundPayment(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment not found with id: " + id));

        payment.setPaymentStatus(PaymentStatus.REFUNDED);

        Payment refundedPayment = paymentRepository.save(payment);

        return modelMapper.map(
                refundedPayment, PaymentResponse.class);
    }
}