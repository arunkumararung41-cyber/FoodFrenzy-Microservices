package com.foodfrenzy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodfrenzy.dto.PaymentRequest;
import com.foodfrenzy.dto.PaymentResponse;
import com.foodfrenzy.service.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // Create Payment
    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse response = paymentService.createPayment(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(
            @PathVariable Long id) {

        PaymentResponse response = paymentService.getPaymentById(id);

        return ResponseEntity.ok(response);
    }

    // Get All Payments
    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {

        List<PaymentResponse> response =
                paymentService.getAllPayments();

        return ResponseEntity.ok(response);
    }

    // Get Payments by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByUserId(
            @PathVariable Long userId) {

        List<PaymentResponse> response =
                paymentService.getPaymentsByUserId(userId);

        return ResponseEntity.ok(response);
    }

    // Get Payment by Order ID
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentByOrderId(
            @PathVariable Long orderId) {

        PaymentResponse response =
                paymentService.getPaymentByOrderId(orderId);

        return ResponseEntity.ok(response);
    }

    // Update Payment Status
    @PutMapping("/{id}/status")
    public ResponseEntity<PaymentResponse> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        PaymentResponse response =
                paymentService.updatePaymentStatus(id, status);

        return ResponseEntity.ok(response);
    }

    // Refund Payment
    @PutMapping("/{id}/refund")
    public ResponseEntity<PaymentResponse> refundPayment(
            @PathVariable Long id) {

        PaymentResponse response =
                paymentService.refundPayment(id);

        return ResponseEntity.ok(response);
    }
}