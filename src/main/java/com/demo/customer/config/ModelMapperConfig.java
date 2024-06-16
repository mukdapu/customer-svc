package com.demo.customer.config;

import com.demo.customer.mapper.CustomModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new CustomModelMapper();
    }
}
