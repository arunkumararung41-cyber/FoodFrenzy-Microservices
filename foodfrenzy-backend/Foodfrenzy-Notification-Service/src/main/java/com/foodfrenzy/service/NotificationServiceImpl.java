package com.foodfrenzy.service;

import org.springframework.stereotype.Service;

import com.foodfrenzy.dto.NotificationRequest;
import com.foodfrenzy.dto.NotificationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Override
    public NotificationResponse sendNotification(
            NotificationRequest request) {

        // For now, simulate sending notification
        System.out.println("=================================");
        System.out.println("Notification Sent");
        System.out.println("User ID   : " + request.getUserId());
        System.out.println("Order ID  : " + request.getOrderId());
        System.out.println("Type      : " + request.getType());
        System.out.println("Message   : " + request.getMessage());
        System.out.println("=================================");

        return new NotificationResponse(
                request.getMessage(),
                request.getType(),
                request.getUserId(),
                request.getOrderId(),
                "SENT"
        );
    }
}