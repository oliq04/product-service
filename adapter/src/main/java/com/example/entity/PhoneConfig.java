package com.example.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PhoneConfig extends ProductConfiguration {
    private String battery;
    private String color;
}
