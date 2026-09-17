package com.foodfrenzy.service;

import java.util.List;

import com.foodfrenzy.dto.CartResponse;
import com.foodfrenzy.dto.NotificationRequest;
import com.foodfrenzy.dto.NotificationResponse;
import com.foodfrenzy.dto.OrderRequest;
import com.foodfrenzy.dto.OrderResponse;
import com.foodfrenzy.dto.PaymentRequest;
import com.foodfrenzy.dto.PaymentResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    OrderResponse getOrderById(Long id);

    List<OrderResponse> getAllOrders();

    List<OrderResponse> getOrdersByUserId(Long userId);

    OrderResponse updateOrderStatus(Long id, String status);

    void cancelOrder(Long id);
    
    List<CartResponse> getCartForUser(Long userId);
    
    PaymentResponse createPayment(PaymentRequest request);
    
    NotificationResponse sendNotification(NotificationRequest request);
}