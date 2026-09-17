package com.foodfrenzy.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.foodfrenzy.dto.CartRequest;
import com.foodfrenzy.dto.CartResponse;
import com.foodfrenzy.entity.Cart;
import com.foodfrenzy.exception.ResourceNotFoundException;
import com.foodfrenzy.repository.CartRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    public CartResponse addToCart(CartRequest request) {

    	    Optional<Cart> existingCart =
    	            cartRepository.findByUserIdAndFoodId(
    	                    request.getUserId(),
    	                    request.getFoodId()
    	            );

    	    if (existingCart.isPresent()) {

    	        Cart cart = existingCart.get();

    	        cart.setQuantity(
    	                cart.getQuantity() + request.getQuantity()
    	        );

    	        Cart updatedCart = cartRepository.save(cart);

    	        return modelMapper.map(updatedCart, CartResponse.class);
    	    }

    	    Cart cart = modelMapper.map(request, Cart.class);

    	    Cart savedCart = cartRepository.save(cart);

    	    return modelMapper.map(savedCart, CartResponse.class);
    	}

    @Override
    public CartResponse getCartItemById(Long id) {

        Cart cart = cartRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart item not found with id: " + id));

        return modelMapper.map(cart, CartResponse.class);
    }

    @Override
    public List<CartResponse> getCartByUserId(Long userId) {

        return cartRepository.findByUserId(userId)
                .stream()
                .map(cart -> modelMapper.map(cart, CartResponse.class))
                .toList();
    }

    @Override
    public CartResponse updateCartItem(Long id, CartRequest request) {

        Cart cart = cartRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart item not found with id: " + id));

        modelMapper.map(request, cart);

        Cart updatedCart = cartRepository.save(cart);

        return modelMapper.map(updatedCart, CartResponse.class);
    }

    @Override
    public void removeFromCart(Long id) {

        if (!cartRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Cart item not found with id: " + id);
        }

        cartRepository.deleteById(id);
    }

	@Override
	@Transactional
	public void clearCart(Long userId) {

		    List<Cart> cartItems = cartRepository.findByUserId(userId);

		    if (cartItems.isEmpty()) {
		        throw new IllegalArgumentException("Cart is already empty");
		    }

		    cartRepository.deleteByUserId(userId);
		
	}
    
}