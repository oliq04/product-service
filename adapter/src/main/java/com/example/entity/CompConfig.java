package com.example.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompConfig extends ProductConfiguration {
    private String ram;
    private String processor;
    private String storage;
}
