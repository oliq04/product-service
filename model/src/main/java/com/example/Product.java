package com.example;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public abstract class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private String type;
    private Long quantity;

}
