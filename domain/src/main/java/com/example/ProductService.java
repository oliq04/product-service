package com.example;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ProductService {

    public final RepositoryProvider repositoryProvider;

    public Product add(Product product) {

        List<ProductOption> optionFromExistingProduct = product.getOptions().stream()
                .filter(Objects::nonNull)
                .map(ProductOption::getProductId)
                .map(id -> repositoryProvider.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Product not with id:" + id + "not found")))
                .map(ProductOption::toProductOption)
                .toList();
        product.setOptions(optionFromExistingProduct);
        return repositoryProvider.save(product);
    }

    public void deleteById(Long id) {
        repositoryProvider.deleteById(id);
    }

    public DomainPage<Product> findAll(int page, int size) {
        DomainPageable domainPageable = new DomainPageable(page, size);
        return repositoryProvider.findAll(domainPageable);
    }

    public Product update(Product newProductInfo) {
        Product product = repositoryProvider.findById(newProductInfo.getId())
                .orElseThrow((() -> new IllegalArgumentException("Product doesn't exist")));
        Product updatedProduct = product.update(newProductInfo);
        return repositoryProvider.save(updatedProduct);
    }

    public Product productConfig(ProductConfiguration productConfiguration) {
        Product product = repositoryProvider.findById(productConfiguration.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        Product configuredProduct = new Product(product.getId(), product.getName(), product.getPrice(), product.getType(),
                product.getQuantity());
        List<Long> configurationIds = productConfiguration.getProductOptionIds();
        List<ProductOption> productOptions = product.getOptions().stream()
                .filter(productOption -> configurationIds.contains(productOption.getId()))
                .toList();
        configuredProduct.setOptions(productOptions);
        return configuredProduct;
    }
}


