package com.foodfrenzy.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class FoodfrenzyAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodfrenzyAdminServerApplication.class, args);
	}

}
