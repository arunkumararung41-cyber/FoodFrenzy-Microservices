package com.foodfrenzy.requests;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserResponse {
    
	private Long id;
	
    private String name;
    
    private String email;
    
    private String role;
   
}
