package com.foodfrenzy.service;

import com.foodfrenzy.dto.NotificationRequest;
import com.foodfrenzy.dto.NotificationResponse;

public interface NotificationService {

    NotificationResponse sendNotification(NotificationRequest request);
}