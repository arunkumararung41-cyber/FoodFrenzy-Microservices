package com.foodfrenzy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodfrenzy.dto.NotificationRequest;
import com.foodfrenzy.dto.NotificationResponse;
import com.foodfrenzy.service.NotificationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponse> sendNotification(
            @Valid @RequestBody NotificationRequest request) {

        NotificationResponse response =
                notificationService.sendNotification(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}