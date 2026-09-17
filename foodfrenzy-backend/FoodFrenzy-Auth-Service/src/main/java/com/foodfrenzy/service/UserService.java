package com.foodfrenzy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodfrenzy.entity.User;
import com.foodfrenzy.exception.AppException;
import com.foodfrenzy.repository.UserRepository;
import com.foodfrenzy.requests.LoginRequest;
import com.foodfrenzy.requests.LoginResponse;
import com.foodfrenzy.requests.RegisterRequest;
import com.foodfrenzy.requests.UserResponse;
import com.foodfrenzy.serviceint.UserServiceInt;

@Service
public class UserService implements UserServiceInt{
    @Autowired
	private  UserRepository urepo;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	public UserResponse register(RegisterRequest request) {
		User existingUser=urepo.findByEmail(request.getEmail()).orElse(null);
		if(existingUser!=null) {
			throw new AppException("User Already Exists!", HttpStatus.CONFLICT);
		}
		User u=mapper.map(request, User.class);
		u.setPassword(passwordEncoder.encode(request.getPassword()));
		u.setRole("USER");
		u=urepo.save(u);
		UserResponse resp=mapper.map(u, UserResponse.class);
		return resp;
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                    request.getEmail(),
	                    request.getPassword()
	            )
	    );

	    User user = urepo.findByEmail(request.getEmail())
	            .orElseThrow(() ->
	                    new UsernameNotFoundException(
	                            "User not found"
	                    )
	            );

	    String token = jwtService.generateToken(
	            user.getEmail(),
	            user.getRole()
	    );

	    UserResponse userResponse =
	            mapper.map(user, UserResponse.class);

	    return new LoginResponse(
	            token,
	            userResponse
	    );
	}
}
