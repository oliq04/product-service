package com.example.config;

import com.example.ProductOptionRepositoryProvider;
import com.example.ProductService;
import com.example.RepositoryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServiceConfig {

    @Bean
    ProductService productService(RepositoryProvider repositoryProvider, ProductOptionRepositoryProvider productOptionRepositoryProvider) {
        return new ProductService(repositoryProvider, productOptionRepositoryProvider);
    }
}
