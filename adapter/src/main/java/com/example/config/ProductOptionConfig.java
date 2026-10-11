package com.example.config;

import com.example.ProductOptionRepositoryProvider;
import com.example.ProductOptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductOptionConfig {

    @Bean
    ProductOptionService productOptionService(ProductOptionRepositoryProvider productOptionRepositoryProvider) {
        return new ProductOptionService(productOptionRepositoryProvider);
    }
}
