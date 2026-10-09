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
        Product editCommand = productMapper.toPojoFromEditCommand(productEditCommand);
        Product updatedProduct = productService.update(editCommand);
        return productMapper.toDto(updatedProduct);
    }
}
