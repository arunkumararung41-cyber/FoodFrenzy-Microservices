package com.foodfrenzy.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "foodfrenzy-cart-service")
public interface CartClient {

    @DeleteMapping("/api/carts/user/{userId}")
    String clearCart(@PathVariable("userId") Long userId);
}