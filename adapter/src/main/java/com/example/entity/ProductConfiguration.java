package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class ProductConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_configuration_seq")
    @SequenceGenerator(
            name = "product_configuration_seq",
            sequenceName = "product_configuration_seq",
            allocationSize = 1
    )
    private Long id;
}
