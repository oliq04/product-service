package com.example.controller;

import com.example.DomainPage;
import com.example.ProductOption;
import com.example.ProductOptionCommand;
import com.example.ProductOptionService;
import com.example.dto.PageDto;
import com.example.dto.ProductOptionDto;
import com.example.mapper.ProductOptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-option")
public class ProductOptionController {
    private final ProductOptionService productOptionService;
    private final ProductOptionMapper productOptionMapper;

    @GetMapping
    public PageDto<ProductOptionDto> getOptions(@RequestParam("page") int page, @RequestParam("size") int size) {
        DomainPage<ProductOption> productOptionDomainPage = productOptionService.getOptions(page, size);
        List<ProductOptionDto> productOptionDtoList = productOptionDomainPage.getContent().stream()
                .map(productOptionMapper::toDto)
                .toList();
        return PageDto.toPageableDto(productOptionDtoList, productOptionDomainPage.getDomainPageDetails());
    }

    @PostMapping
    public ProductOptionDto addOption(@RequestBody ProductOptionCommand productOptionCommand) {
        ProductOption productOption = productOptionMapper.toPojoFromCommand(productOptionCommand);
        ProductOption addedOption = productOptionService.addOption(productOption);
        return productOptionMapper.toDto(addedOption);
    }

    @PatchMapping("/{id}")
    public ProductOptionDto editOption(@PathVariable("id") Long id, @RequestBody ProductOptionCommand productOptionCommand) {
        ProductOption productOption = productOptionMapper.toPojoFromCommand(productOptionCommand);
        ProductOption updatedProductOption = productOptionService.editOption(id, productOption);
        return productOptionMapper.toDto(updatedProductOption);
    }

    @DeleteMapping
    public void deleteOption(Long id) {
        productOptionService.deleteOption(id);
    }
}
