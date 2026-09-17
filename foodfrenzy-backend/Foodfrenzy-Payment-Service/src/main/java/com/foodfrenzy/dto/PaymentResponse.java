package com.foodfrenzy.dto;

import java.time.LocalDateTime;

import com.foodfrenzy.entity.PaymentMethod;
import com.foodfrenzy.entity.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private Long id;

    private Long orderId;

    private Long userId;

    private Double amount;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private LocalDateTime paymentDate;
}