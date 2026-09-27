package com.example;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService {

    public final RepositoryProvider repositoryProvider;

    public Product add(Product product) {
        return repositoryProvider.save(product);
    }

    public void deleteById(Long id) {
        repositoryProvider.deleteById(id);
    }

    public DomainPage<Product> findAll(int page, int size) {
        DomainPageable domainPageable = new DomainPageable(page,size);
        return repositoryProvider.findAll(domainPageable);
    }


}
