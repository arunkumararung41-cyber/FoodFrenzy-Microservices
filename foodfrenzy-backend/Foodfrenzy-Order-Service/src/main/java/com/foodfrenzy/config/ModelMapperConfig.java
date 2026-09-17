package com.foodfrenzy.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foodfrenzy.dto.OrderRequest;
import com.foodfrenzy.entity.Order;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(OrderRequest.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId));

        return modelMapper;
    }
}