package com.example;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String type;
    private Long quantity;
    private String ram;
    private String processor;
    private String storage;
    private String battery;
    private String color;
}
