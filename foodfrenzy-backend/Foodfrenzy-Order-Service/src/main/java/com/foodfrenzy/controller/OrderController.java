package com.foodfrenzy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodfrenzy.dto.CartResponse;
import com.foodfrenzy.dto.NotificationRequest;
import com.foodfrenzy.dto.NotificationResponse;
import com.foodfrenzy.dto.OrderRequest;
import com.foodfrenzy.dto.OrderResponse;
import com.foodfrenzy.dto.PaymentRequest;
import com.foodfrenzy.dto.PaymentResponse;
import com.foodfrenzy.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Create Order
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest request) {

        OrderResponse response = orderService.createOrder(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable Long id) {

        OrderResponse response = orderService.getOrderById(id);

        return ResponseEntity.ok(response);
    }

    // Get All Orders
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {

        List<OrderResponse> response = orderService.getAllOrders();

        return ResponseEntity.ok(response);
    }

    // Get Orders by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(
            @PathVariable Long userId) {

        List<OrderResponse> response =
                orderService.getOrdersByUserId(userId);

        return ResponseEntity.ok(response);
    }

    // Update Order Status
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        OrderResponse response =
                orderService.updateOrderStatus(id, status);

        return ResponseEntity.ok(response);
    }

    // Cancel Order
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(
            @PathVariable Long id) {

        orderService.cancelOrder(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/cart/{userId}")
    public ResponseEntity<List<CartResponse>> getCartForUser(
            @PathVariable Long userId) {
    	List<CartResponse> response=orderService.getCartForUser(userId);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/payment")
    public ResponseEntity<PaymentResponse> createPayment(
            @Valid @RequestBody PaymentRequest request) {

        return ResponseEntity.ok(
                orderService.createPayment(request)
        );
    }
    @PostMapping("/notification")
    public ResponseEntity<NotificationResponse> sendNotification(
            @Valid @RequestBody NotificationRequest request) {

        return ResponseEntity.ok(
                orderService.sendNotification(request)
        );
    }
}