package com.foodfrenzy.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.foodfrenzy.dto.CartResponse;

import java.util.List;

@FeignClient(name = "FOODFRENZY-CART-SERVICE")
public interface CartClient {

    @GetMapping("/api/carts/user/{userId}")
    List<CartResponse> getCartByUserId(@PathVariable("userId") Long userId);
}