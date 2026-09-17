package com.foodfrenzy.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "foodfrenzy-order-service")
public interface OrderClient {

    @PutMapping("/api/orders/{id}/status")
    Object updateOrderStatus(
            @PathVariable("id") Long orderId,
            @RequestParam("status") String status);
}