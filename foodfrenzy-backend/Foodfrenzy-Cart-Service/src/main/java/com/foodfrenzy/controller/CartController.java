package com.foodfrenzy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodfrenzy.dto.CartRequest;
import com.foodfrenzy.dto.CartResponse;
import com.foodfrenzy.service.CartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // Add item to cart
    @PostMapping
    public ResponseEntity<CartResponse> addToCart(
            @Valid @RequestBody CartRequest request) {

        CartResponse response = cartService.addToCart(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get cart item by ID
    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCartItemById(
            @PathVariable Long id) {

        CartResponse response = cartService.getCartItemById(id);

        return ResponseEntity.ok(response);
    }

    // Get cart by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartResponse>> getCartByUserId(
            @PathVariable Long userId) {

        List<CartResponse> response =
                cartService.getCartByUserId(userId);

        return ResponseEntity.ok(response);
    }

    // Update cart item
    @PutMapping("/{id}")
    public ResponseEntity<CartResponse> updateCartItem(
            @PathVariable Long id,
            @Valid @RequestBody CartRequest request) {

        CartResponse response =
                cartService.updateCartItem(id, request);

        return ResponseEntity.ok(response);
    }

    // Remove item from cart
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCart(
            @PathVariable Long id) {

        cartService.removeFromCart(id);

        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> clearCart(
            @PathVariable Long userId) {

        cartService.clearCart(userId);

        return ResponseEntity.ok(
                "Cart cleared successfully"
        );
    }
}