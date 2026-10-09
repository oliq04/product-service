package com.example;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductOptionCommand {
    private Long productId;
    private String optionType;
    private String value;
    private BigDecimal additionalPrice;
    private boolean available = true;
}
