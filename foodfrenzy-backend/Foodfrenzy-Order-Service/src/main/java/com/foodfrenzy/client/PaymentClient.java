package com.foodfrenzy.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.foodfrenzy.dto.PaymentRequest;
import com.foodfrenzy.dto.PaymentResponse;

@FeignClient(name = "foodfrenzy-payment-service")
public interface PaymentClient {

    @PostMapping("/api/payments")
    PaymentResponse createPayment(@RequestBody PaymentRequest request);
}