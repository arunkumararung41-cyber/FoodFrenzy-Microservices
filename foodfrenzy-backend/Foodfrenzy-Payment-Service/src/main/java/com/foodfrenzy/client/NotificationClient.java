package com.foodfrenzy.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.foodfrenzy.dto.NotificationRequest;
import com.foodfrenzy.dto.NotificationResponse;

@FeignClient(name = "foodfrenzy-notification-service")
public interface NotificationClient {

    @PostMapping("/api/notifications")
    NotificationResponse sendNotification(
            @RequestBody NotificationRequest request);
}