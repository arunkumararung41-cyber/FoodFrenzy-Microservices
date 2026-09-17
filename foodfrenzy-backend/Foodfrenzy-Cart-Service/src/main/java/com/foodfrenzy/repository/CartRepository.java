package com.foodfrenzy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodfrenzy.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);

    Optional<Cart> findByUserIdAndFoodId(Long userId, Long foodId);
    
    void deleteByUserId(Long userId);
}