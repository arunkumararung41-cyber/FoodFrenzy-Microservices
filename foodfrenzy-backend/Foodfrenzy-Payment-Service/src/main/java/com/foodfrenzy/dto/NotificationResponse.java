package com.foodfrenzy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private String message;
    private String type;
    private Long userId;
    private Long orderId;
    private String status;
}