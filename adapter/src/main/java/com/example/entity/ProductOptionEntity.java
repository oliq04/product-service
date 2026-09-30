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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_option_seq")
    @SequenceGenerator(name = "product_option_seq", sequenceName = "product_option_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(nullable = false)
    private String optionType;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private BigDecimal additionalPrice;

    @Column(nullable = false)
    private boolean available;
}
