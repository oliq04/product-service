package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductOptionDto {
    private Long id;
    private String optionType;
    private String value;
    private BigDecimal additionalPrice;
    private boolean available;
}
