package com.foodfrenzy.service;

import java.util.List;

import com.foodfrenzy.dto.FoodRequest;
import com.foodfrenzy.dto.FoodResponse;

public interface FoodService {

    FoodResponse createFood(FoodRequest request);

    FoodResponse getFoodById(Long id);

    List<FoodResponse> getAllFoods();

    FoodResponse updateFood(Long id, FoodRequest request);

    void deleteFood(Long id);
}