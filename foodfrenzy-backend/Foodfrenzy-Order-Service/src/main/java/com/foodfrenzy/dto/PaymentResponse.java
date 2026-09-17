package com.foodfrenzy.dto;

import com.foodfrenzy.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private Long id;
    private Long orderId;
    private Long userId;
    private Double amount;
    private PaymentMethod paymentMethod;
    private String paymentStatus;
    private LocalDateTime paymentDate;
}