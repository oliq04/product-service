package com.example;

import com.example.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RepositoryProviderImpl implements RepositoryProvider {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        ProductEntity savedEntity = productRepository.save(productEntity);
        return productMapper.toPojoFromEntity(savedEntity);
    }

    @Override
    public void delete(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        productRepository.delete(productEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toPojoFromEntity);
    }

    @Override
    public DomainPage<Product> findAll(DomainPageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPage(), pageable.getSize());
        Page<ProductEntity> productEntities = productRepository.findAll(pageRequest);
        return getProductPage(productEntities);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private @NonNull DomainPage<Product> getProductPage(Page<ProductEntity> result) {
        List<Product> productList = result.getContent().stream()
                .map(productMapper::toPojoFromEntity)
                .toList();
        DomainPageDetails domainPageDetails = new DomainPageDetails(result.getSize(), result.getNumber(),
                result.getNumberOfElements(), result.getTotalPages());
        return DomainPage.toPageable(productList, domainPageDetails);
    }
}
