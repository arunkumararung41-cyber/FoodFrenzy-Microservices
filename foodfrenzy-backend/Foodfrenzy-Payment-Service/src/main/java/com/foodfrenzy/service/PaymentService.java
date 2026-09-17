package com.foodfrenzy.service;

import java.util.List;

import com.foodfrenzy.dto.PaymentRequest;
import com.foodfrenzy.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest request);

    PaymentResponse getPaymentById(Long id);

    List<PaymentResponse> getAllPayments();

    List<PaymentResponse> getPaymentsByUserId(Long userId);

    PaymentResponse getPaymentByOrderId(Long orderId);

    PaymentResponse updatePaymentStatus(Long id, String status);

    PaymentResponse refundPayment(Long id);
}