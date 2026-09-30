package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private String type;
    private Long quantity;
    private List<ProductOption> options = new ArrayList<>();

    public Product(Long id, String name, BigDecimal price, String type, Long quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }
}
