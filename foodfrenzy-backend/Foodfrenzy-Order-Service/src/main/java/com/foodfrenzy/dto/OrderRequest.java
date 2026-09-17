package com.foodfrenzy.dto;

import com.foodfrenzy.entity.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;
}