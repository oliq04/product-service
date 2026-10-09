package com.example.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_option")
@Getter
@Setter
public class ProductOptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity optionProduct;

    @Column(nullable = false)
    private String optionType;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private BigDecimal additionalPrice;

    @Column(nullable = false)
    private boolean available;
}
