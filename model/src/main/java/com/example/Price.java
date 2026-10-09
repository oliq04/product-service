package com.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class Price {
    private BigDecimal taxIncludedAmount;
    private BigDecimal netPrice;
    private Double taxRate;
}
