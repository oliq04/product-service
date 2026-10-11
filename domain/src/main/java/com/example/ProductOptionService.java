package com.example;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductOptionRepositoryProvider productOptionServiceProvider;

    public DomainPage<ProductOption> getOptions(int page, int size) {
        return productOptionServiceProvider.findAll(page, size);
    }

    public ProductOption addOption(ProductOption productOption) {
        return productOptionServiceProvider.save(productOption);
    }

    public ProductOption editOption(Long id, ProductOption productOptionCommand) {
        ProductOption productOption = productOptionServiceProvider.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product option"));

        ProductOption updatedOption = productOption.update(productOptionCommand);
        return productOptionServiceProvider.save(updatedOption);
    }

    public void deleteOption(Long id) {
        productOptionServiceProvider.deleteById(id);
    }
}
