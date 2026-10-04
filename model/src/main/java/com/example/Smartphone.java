package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Smartphone extends Product {
    private String battery;
    private String color;
    private List<Accessory> accessories;

    public Smartphone(Long id, String name, BigDecimal price, String type, Long quantity, String battery,
                      String color, List<Accessory> accessories) {
        super(id,name,price,type,quantity);
        this.battery = battery;
        this.color = color;
        this.accessories = accessories;
    }

}
