package com.foodfrenzy.dto;

import java.time.LocalDateTime;

import com.foodfrenzy.entity.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;

    private Long userId;

    private Double totalAmount;

    private OrderStatus status;

    private LocalDateTime orderDate;
}