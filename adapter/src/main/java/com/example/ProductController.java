package com.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductCommand productCommand) {
        log.info("Retrieved add product request: {}", productCommand);
        Product newProduct = productMapper.toPojo(productCommand);
        Product savedProduct = productService.add(newProduct);
        return productMapper.toDto(savedProduct);
    }

    @GetMapping
    public PageDto<ProductDto> findProducts(@RequestParam("page") int page,
                                            @RequestParam("size") int size) {
        DomainPage<Product> domainPage = productService.findAll(page, size);
        List<ProductDto> productDtoList = domainPage.getContent().stream()
                .map(productMapper::toDto)
                .toList();
        return PageDto.toPageableDto(productDtoList, domainPage.getDomainPageDetails());
    }

    @DeleteMapping
    public void deleteById(Long id) {
        productService.deleteById(id);
    }

    @PostMapping("/to-cart")
    public ConfiguredProductDto configuredProduct(ProductConfigurationCommand productConfigurationCommand) {
        ProductConfiguration productConfiguration = productMapper.toProductConfig(productConfigurationCommand);
        return productService.productConfig(productConfiguration);
    }

}
