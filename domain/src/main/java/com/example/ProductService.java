package com.example;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ProductService {

    public final RepositoryProvider repositoryProvider;
    public final ProductOptionRepositoryProvider productOptionRepositoryProvider;

    public Product add(Product product, List<Long> existingProducts) {
        if (existingProducts != null) {
            List<ProductOption> optionFromExistingProduct = existingProducts.stream()
                    .filter(Objects::nonNull)
                    .map(id -> repositoryProvider.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Product not with id: " + id + " not found")))
                    .map(ProductOption::toProductOption)
                    .toList();
            product.getOptions().addAll(optionFromExistingProduct);
        }
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
        Product savedProduct = repositoryProvider.save(updatedProduct);

        // A product can be used by many configurations, so every denormalized
        // product option pointing to it has to be refreshed after the product update.
        productOptionRepositoryProvider.findByProductId(product.getId()).stream()
                .map(productOption -> productOption.updateFromProduct(savedProduct))
                .forEach(productOptionRepositoryProvider::save);

        return savedProduct;
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


