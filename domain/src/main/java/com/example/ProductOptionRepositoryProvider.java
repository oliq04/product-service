package com.example;

import java.util.List;
import java.util.Optional;

public interface ProductOptionRepositoryProvider {
    void deleteById(Long id);

    Optional<ProductOption> findById(Long id);

    List<ProductOption> findByProductId(Long id);

    ProductOption save(ProductOption productOption);

    DomainPage<ProductOption> findAll(int page, int size);
}
