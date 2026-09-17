package com.foodfrenzy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodfrenzy.dto.FoodRequest;
import com.foodfrenzy.dto.FoodResponse;
import com.foodfrenzy.service.FoodService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    // Create Food
    @PostMapping
    public ResponseEntity<FoodResponse> createFood(
            @Valid @RequestBody FoodRequest request) {

        FoodResponse response = foodService.createFood(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Food by ID
    @GetMapping("/{id}")
    public ResponseEntity<FoodResponse> getFoodById(
            @PathVariable Long id) {

        FoodResponse response = foodService.getFoodById(id);

        return ResponseEntity.ok(response);
    }

    // Get All Foods
    @GetMapping
    public ResponseEntity<List<FoodResponse>> getAllFoods() {

        List<FoodResponse> response = foodService.getAllFoods();

        return ResponseEntity.ok(response);
    }

    // Update Food
    @PutMapping("/{id}")
    public ResponseEntity<FoodResponse> updateFood(
            @PathVariable Long id,
            @Valid @RequestBody FoodRequest request) {

        FoodResponse response = foodService.updateFood(id, request);

        return ResponseEntity.ok(response);
    }

    // Delete Food
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(
            @PathVariable Long id) {

        foodService.deleteFood(id);

        return ResponseEntity.noContent().build();
    }
}