package com.foodfrenzy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodfrenzy.entity.User;
import com.foodfrenzy.requests.LoginRequest;
import com.foodfrenzy.requests.LoginResponse;
import com.foodfrenzy.requests.RegisterRequest;
import com.foodfrenzy.requests.UserResponse;
import com.foodfrenzy.response.ApiResponse;
import com.foodfrenzy.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	private final UserService uservice;
	
	public UserController(UserService uservice) {
		this.uservice=uservice;
	}
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		UserResponse dto=uservice.register(request);
		 ApiResponse response=new ApiResponse<>("user registered successfully",dto,HttpStatus.CREATED);
		return ResponseEntity.ok(response) ;
	}
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(
	        @Valid @RequestBody LoginRequest request) {

	    LoginResponse dto = uservice.login(request);
	    ApiResponse response=new ApiResponse<>("user login successfully",dto,HttpStatus.OK);
		return ResponseEntity.ok(response) ;
	}
}
