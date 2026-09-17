package com.foodfrenzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FoodfrenzyNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodfrenzyNotificationServiceApplication.class, args);
	}

}
