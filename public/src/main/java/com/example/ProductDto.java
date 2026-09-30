package com.example;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String type;
    private Long quantity;
    private List<ProductOptionDto> options;
}
