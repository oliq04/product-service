package com.example.dto;

import com.example.ProductOptionCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductEditCommand {
    private Long id;
    private String name;
    private PriceCommand price;
    private String type;
    private Long quantity;
    private List<ProductOptionCommand> options;
}
