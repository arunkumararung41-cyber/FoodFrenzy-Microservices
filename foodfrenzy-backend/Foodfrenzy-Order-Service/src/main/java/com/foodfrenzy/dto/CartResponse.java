package com.foodfrenzy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    private Long id;
    private Long userId;
    private Long foodId;
    private Integer quantity;
    private Double price;
}