package com.foodfrenzy.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.foodfrenzy.dto.FoodRequest;
import com.foodfrenzy.dto.FoodResponse;
import com.foodfrenzy.entity.Food;
import com.foodfrenzy.exception.ResourceNotFoundException;
import com.foodfrenzy.repository.FoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImp implements FoodService {

    private final FoodRepository foodRepository;
    private final ModelMapper modelMapper;

    @Override
    public FoodResponse createFood(FoodRequest request) {

        Food food = modelMapper.map(request, Food.class);

        Food savedFood = foodRepository.save(food);

        return modelMapper.map(savedFood, FoodResponse.class);
    }

    @Override
    public FoodResponse getFoodById(Long id) {

        Food food = foodRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Food not found with id: " + id));

        return modelMapper.map(food, FoodResponse.class);
    }

    @Override
    public List<FoodResponse> getAllFoods() {

        return foodRepository.findAll()
                .stream()
                .map(food -> modelMapper.map(food, FoodResponse.class))
                .toList();
    }

    @Override
    public FoodResponse updateFood(Long id, FoodRequest request) {

        Food food = foodRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Food not found with id: " + id));

        modelMapper.map(request, food);

        Food updatedFood = foodRepository.save(food);

        return modelMapper.map(updatedFood, FoodResponse.class);
    }

    @Override
    public void deleteFood(Long id) {

        if (!foodRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Food not found with id: " + id);
        }

        foodRepository.deleteById(id);
    }
}