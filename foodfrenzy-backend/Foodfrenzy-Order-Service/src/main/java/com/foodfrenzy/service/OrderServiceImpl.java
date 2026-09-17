package com.foodfrenzy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.foodfrenzy.client.CartClient;
import com.foodfrenzy.client.NotificationClient;
import com.foodfrenzy.client.PaymentClient;
import com.foodfrenzy.dto.CartResponse;
import com.foodfrenzy.dto.NotificationRequest;
import com.foodfrenzy.dto.NotificationResponse;
import com.foodfrenzy.dto.OrderRequest;
import com.foodfrenzy.dto.OrderResponse;
import com.foodfrenzy.dto.PaymentRequest;
import com.foodfrenzy.dto.PaymentResponse;
import com.foodfrenzy.entity.Order;
import com.foodfrenzy.entity.OrderStatus;
import com.foodfrenzy.exception.ResourceNotFoundException;
import com.foodfrenzy.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CartClient cartClient;
    private final PaymentClient paymentClient;
    private final NotificationClient notificationClient;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
    	
    	Optional<Order> existingOrder =
    	        orderRepository.findByUserIdAndStatus(
    	                request.getUserId(),
    	                OrderStatus.PENDING);

    	if (existingOrder.isPresent()) {
    	    throw new IllegalArgumentException(
    	            "User already has a pending order");
    	}
    	    // 1. Get cart items
    	    List<CartResponse> cartItems =
    	            cartClient.getCartByUserId(request.getUserId());

    	    // 2. Check cart
    	    if (cartItems == null || cartItems.isEmpty()) {
    	        throw new IllegalArgumentException("Cart is empty");
    	    }

    	    // 3. Calculate total amount
    	    double totalAmount = cartItems.stream()
    	            .mapToDouble(item ->
    	                    item.getPrice() * item.getQuantity())
    	            .sum();

    	    // 4. Create Order
    	    Order order = new Order();

    	    order.setUserId(request.getUserId());
    	    order.setTotalAmount(totalAmount);
    	    order.setStatus(OrderStatus.PENDING);
    	    order.setOrderDate(LocalDateTime.now());

    	    Order savedOrder = orderRepository.save(order);

    	 // 5. Create Payment
    	    PaymentRequest paymentRequest = new PaymentRequest(
    	            savedOrder.getId(),
    	            request.getUserId(),
    	            totalAmount,
    	            request.getPaymentMethod()
    	    );

    	    PaymentResponse paymentResponse =
    	            paymentClient.createPayment(paymentRequest);

    	    // 6. Update Order Status
    	    if ("SUCCESS".equals(paymentResponse.getPaymentStatus())) {
    	        savedOrder.setStatus(OrderStatus.CONFIRMED);
    	        savedOrder = orderRepository.save(savedOrder);
    	    }

    	    // 7. Send Notification
    	    NotificationRequest notificationRequest =
    	            new NotificationRequest(
    	                    request.getUserId(),
    	                    savedOrder.getId(),
    	                    "Your order has been placed successfully!",
    	                    "ORDER_PLACED"
    	            );

    	    notificationClient.sendNotification(notificationRequest);

    	    // 8. Return Order
    	    return modelMapper.map(savedOrder, OrderResponse.class);
    	    	
    }

    @Override
    public OrderResponse getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id: " + id));

        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(
                        order, OrderResponse.class))
                .toList();
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {

        return orderRepository.findByUserId(userId)
                .stream()
                .map(order -> modelMapper.map(
                        order, OrderResponse.class))
                .toList();
    }

    @Override
    public OrderResponse updateOrderStatus(
            Long id, String status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id: " + id));

        try {
            OrderStatus orderStatus =
                    OrderStatus.valueOf(status.toUpperCase());

            order.setStatus(orderStatus);

        } catch (IllegalArgumentException ex) {

            throw new IllegalArgumentException(
                    "Invalid order status: " + status);
        }

        Order updatedOrder = orderRepository.save(order);

        return modelMapper.map(
                updatedOrder, OrderResponse.class);
    }

    @Override
    public void cancelOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id: " + id));

        order.setStatus(OrderStatus.CANCELLED);

        orderRepository.save(order);
    }

	@Override
	public List<CartResponse> getCartForUser(Long userId) {
		 return cartClient.getCartByUserId(userId);
		 
	}

	@Override
	public PaymentResponse createPayment(PaymentRequest request) {
		    return paymentClient.createPayment(request);
		}

	@Override
	public NotificationResponse sendNotification(NotificationRequest request) {    
		    return notificationClient.sendNotification(request);
	}
  
}