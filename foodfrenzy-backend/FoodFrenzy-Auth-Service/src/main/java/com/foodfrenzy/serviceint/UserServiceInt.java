package com.foodfrenzy.serviceint;

import com.foodfrenzy.requests.LoginRequest;
import com.foodfrenzy.requests.LoginResponse;
import com.foodfrenzy.requests.RegisterRequest;
import com.foodfrenzy.requests.UserResponse;

public interface UserServiceInt {

	 UserResponse register(RegisterRequest request);
	
	LoginResponse login(LoginRequest request);
}
