package com.example.controller;

import com.example.*;
import com.example.dto.PageDto;
import com.example.dto.ProductDto;
import com.example.dto.ProductEditCommand;
import com.example.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        List<Long> existingProducts = productCommand.getExistingProductsId();
        Product newProduct = productMapper.toPojo(productCommand);
        Product savedProduct = productService.add(newProduct, existingProducts);
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }

    @PostMapping("/to-cart")
    public ProductDto configuredProduct(@RequestBody ProductConfigurationCommand productConfigurationCommand) {
        log.info("Retrieved product configuration request {}", productConfigurationCommand);
        ProductConfiguration productConfiguration = productMapper.toProductConfig(productConfigurationCommand);
        Product configuredProduct = productService.productConfig(productConfiguration);
        return productMapper.toDto(configuredProduct);
    }

    @PatchMapping
    public ProductDto updateProductInfo(@RequestBody ProductEditCommand productEditCommand) {
        List<Long> existingProducts = productEditCommand.getExistingProductsId();
        Product editCommand = productMapper.toPojoFromEditCommand(productEditCommand);
        Product updatedProduct = productService.update(editCommand, existingProducts);
        return productMapper.toDto(updatedProduct);
    }

    @PostMapping("/configured")
    public List<ProductDto> getConfiguredProducts(@RequestBody Map<Long, List<Long>> productsWithConfigs) {
        List<Product> productList = productService.getConfiguredList(productsWithConfigs);
        return productList.stream().map(productMapper::toDto)
                .toList();
    }
}
