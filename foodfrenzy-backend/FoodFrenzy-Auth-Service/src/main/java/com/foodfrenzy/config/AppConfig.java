package com.foodfrenzy.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.foodfrenzy.service.CustomUserDetailsService;
@Configuration
public class AppConfig {
	@Autowired
	private CustomUserDetailsService userDetailsService;
   @Autowired
   private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(
	        HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())

	        .cors(cors -> {})

	        .sessionManagement(session ->
	                session.sessionCreationPolicy(
	                        SessionCreationPolicy.STATELESS
	                )
	        )

	        .authorizeHttpRequests(auth -> auth
	                .requestMatchers(
	                        "/api/auth/register",
	                        "/api/auth/login"
	                ).permitAll()
	                .anyRequest().authenticated()
	        )

	        .authenticationProvider(authenticationProvider())

	        .addFilterBefore(
	                jwtAuthenticationFilter,
	                UsernamePasswordAuthenticationFilter.class
	        );

	    return http.build();
	}
}
