package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Price price;
    private String type;
    private Long quantity;
    private List<ProductOption> options = new ArrayList<>();

    public Product(Long id, String name, Price price, String type, Long quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }

    public Product update(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.options = product.getOptions();
        return this;
    }
}
