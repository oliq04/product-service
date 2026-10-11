package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOption {
    private Long id;
    private Long productId;
    private String optionType;
    private String value;
    private BigDecimal additionalPrice;
    private String name;
    private boolean available;

    public static ProductOption toProductOption(Product product) {
        return new ProductOption(null, product.getId(), product.getType(), product.getName(),
                product.getPrice().getTaxIncludedAmount(), product.getName(), true);
    }

    public ProductOption update(ProductOption productOptionCommand) {
        this.optionType = productOptionCommand.getOptionType();
        this.value = productOptionCommand.getValue();
        this.additionalPrice = productOptionCommand.getAdditionalPrice();
        this.name = productOptionCommand.getName();
        return this;
    }

    public ProductOption updateFromProduct(Product updatedProduct) {
        this.optionType = updatedProduct.getType();
        this.value = updatedProduct.getName();
        this.additionalPrice = updatedProduct.getPrice().getTaxIncludedAmount();
        this.name = updatedProduct.getName();
        return this;
    }
}
