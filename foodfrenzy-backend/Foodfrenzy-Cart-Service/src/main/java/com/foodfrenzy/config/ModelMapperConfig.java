package com.foodfrenzy.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foodfrenzy.dto.CartRequest;
import com.foodfrenzy.entity.Cart;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(CartRequest.class, Cart.class)
                .addMappings(mapper -> mapper.skip(Cart::setId));

        return modelMapper;
    }
}