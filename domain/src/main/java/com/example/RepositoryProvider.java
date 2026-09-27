package com.example;

import java.util.Optional;

public interface RepositoryProvider {
    Product save(Product product);
    void delete(Product product);
    Optional<Product> findById(Long id);
    DomainPage<Product> findAll(DomainPageable domainPageable);
    void deleteById(Long id);

}
