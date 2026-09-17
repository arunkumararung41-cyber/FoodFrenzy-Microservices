package com.foodfrenzy.service;

import java.util.List;

import com.foodfrenzy.dto.CartRequest;
import com.foodfrenzy.dto.CartResponse;

public interface CartService {

    CartResponse addToCart(CartRequest request);

    CartResponse getCartItemById(Long id);

    List<CartResponse> getCartByUserId(Long userId);

    CartResponse updateCartItem(Long id, CartRequest request);

    void removeFromCart(Long id);
    
    void clearCart(Long userId);
}