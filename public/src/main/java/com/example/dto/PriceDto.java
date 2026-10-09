package com.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class PriceDto {
    private BigDecimal taxIncludedAmount;
    private BigDecimal netPrice;
    private Double taxRate;
}
