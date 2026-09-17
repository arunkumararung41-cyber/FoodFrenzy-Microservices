package com.foodfrenzy.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

	private String message;
	
	private T response;
	
	private HttpStatus httpstatus;
}
