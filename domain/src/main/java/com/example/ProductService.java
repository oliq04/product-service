package com.example;

import lombok.RequiredArgsConstructor;

import java.util.List;

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
        DomainPageable domainPageable = new DomainPageable(page, size);
        return repositoryProvider.findAll(domainPageable);
    }

    public Product productConfig(ProductConfiguration productConfiguration) {
        Product product = repositoryProvider.findById(productConfiguration.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        String productType = product.getType();
        switch (productType) {
            case "PHONE": {
                ProductOption battery = product.getOptions()
                        .stream()
                        .filter(productOption -> productOption.getId().equals(productConfiguration.getBatteryId()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Wrong battery id"));
                ProductOption color = product.getOptions()
                        .stream()
                        .filter(productOption -> productOption.getId().equals(productConfiguration.getColorId()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Wrong color id"));

                List<String> accessories = product.getOptions()
                        .stream()
                        .filter(productOption -> productConfiguration.getAccessoriesList().contains(productOption.getId()))
                        .map(ProductOption::getValue)
                        .toList();
                return new Smartphone(product.getId(), product.getName(), product.getPrice(),
                        product.getType(), product.getQuantity(),battery.getValue(), color.getValue(),accessories);
            }

            case "COMPUTER":{
                ProductOption ram = product.getOptions().stream()
                        .filter(productOption -> productOption.getId().equals(productConfiguration.getRamId()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid RAM id"));
                ProductOption processor = product.getOptions().stream()
                        .filter(productOption -> productOption.getId().equals(productConfiguration.getProcessorId()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid processor id"));
                return new Computer(product.getId(),product.getName(), product.getPrice(), product.getType(),
                        product.getQuantity(), ram.getValue(), processor.getValue());
            }

            default:
                throw new IllegalArgumentException("Illegal type");
        }
    }


}
