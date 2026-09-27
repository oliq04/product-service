package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProductServiceConfig {

    @Bean
    ProductService productService(RepositoryProvider repositoryProvider) {
        return new ProductService(repositoryProvider);
    }
}
