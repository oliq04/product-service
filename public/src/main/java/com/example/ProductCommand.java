package com.example;

import com.example.dto.PriceCommand;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProductCommand {
    private Integer id;
    private String name;
    private PriceCommand price;
    private String type;
    private Long quantity;
    private List<Long> existingProductsId;
    private List<ProductOptionCommand> options;
}
