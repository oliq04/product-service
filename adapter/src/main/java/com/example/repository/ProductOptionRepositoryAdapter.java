package com.example.repository;

import com.example.*;
import com.example.entity.ProductOptionEntity;
import com.example.mapper.ProductOptionMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProductOptionRepositoryAdapter implements ProductOptionRepositoryProvider {
    private final ProductOptionRepository productOptionRepository;
    private final ProductOptionMapper productOptionMapper;
    private final ProductRepository productRepository;

    @Override
    public void deleteById(Long id) {
        productOptionRepository.deleteById(id);
    }

    @Override
    public Optional<ProductOption> findById(Long id) {
        return productOptionRepository.findById(id)
                .map(productOptionMapper::toPojo);
    }

    @Override
    public List<ProductOption> findByProductId(Long id) {
        return productOptionRepository.findAllByOptionProduct_Id(id).stream()
                .map(productOptionMapper::toPojo)
                .toList();
    }

    @Override
    public ProductOption save(ProductOption productOption) {
        ProductOptionEntity productEntity = productOption.getId() == null
                ? productOptionMapper.toEntity(productOption)
                : productOptionRepository.findById(productOption.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product option"));

        productOptionMapper.updateEntity(productOption, productEntity);

        if (productEntity.getOptionProduct() == null && productOption.getProductId() != null) {
            productEntity.setOptionProduct(productRepository.getReferenceById(productOption.getProductId()));
        }

        ProductOptionEntity savedEntity = productOptionRepository.save(productEntity);
        return productOptionMapper.toPojo(savedEntity);
    }

    @Override
    public DomainPage<ProductOption> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ProductOptionEntity> productEntityPage = productOptionRepository.findAll(pageRequest);
        return getProductPage(productEntityPage);
    }

    private @NonNull DomainPage<ProductOption> getProductPage(Page<ProductOptionEntity> result) {
        List<ProductOption> productList = result.getContent().stream()
                .map(productOptionMapper::toPojo)
                .toList();
        DomainPageDetails domainPageDetails = new DomainPageDetails(result.getSize(), result.getNumber(),
                result.getNumberOfElements(), result.getTotalPages());
        return DomainPage.toPageable(productList, domainPageDetails);
    }
}
