package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Computer extends Product {
    private String ram;
    private String processor;

    public Computer(Long id, String name, BigDecimal price, String type, Long quantity,
                    String ram, String processor) {
        super(id, name, price, type, quantity);
        this.ram = ram;
        this.processor = processor;
    }
}
