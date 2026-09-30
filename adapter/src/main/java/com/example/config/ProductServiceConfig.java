package com.example.config;

import com.example.ProductService;
import com.example.RepositoryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProductServiceConfig {

    @Bean
    ProductService productService(RepositoryProvider repositoryProvider) {
        return new ProductService(repositoryProvider);
    }
}
