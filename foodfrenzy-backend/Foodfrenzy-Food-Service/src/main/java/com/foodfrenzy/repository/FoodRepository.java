package com.foodfrenzy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodfrenzy.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

}