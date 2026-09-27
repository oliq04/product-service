package com.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Computer extends Product {
    private String ram;
    private String processor;
    private String storage;
}
