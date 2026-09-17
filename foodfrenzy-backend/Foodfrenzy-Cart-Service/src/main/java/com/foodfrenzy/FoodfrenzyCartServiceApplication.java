package com.foodfrenzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FoodfrenzyCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodfrenzyCartServiceApplication.class, args);
	}

}
