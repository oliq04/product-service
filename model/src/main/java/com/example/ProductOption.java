package com.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductOption {
    private Long id;
    private String optionType;
    private String value;
    private BigDecimal additionalPrice;
    private boolean available;
}
