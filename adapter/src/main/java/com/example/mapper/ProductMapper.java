package com.example.mapper;

import com.example.*;
import com.example.dto.ProductDto;
import com.example.dto.ProductEditCommand;
import com.example.dto.ProductOptionDto;
import com.example.entity.ProductEntity;
import com.example.entity.ProductOptionEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toPojo(ProductCommand command);

    ProductDto toDto(Product product);

    ProductEntity toEntity(Product product);

    Product toPojoFromEntity(ProductEntity entity);

    ProductOption toPojo(ProductOptionCommand command);

    ProductOptionDto toDto(ProductOption option);

    @Mapping(target = "optionProduct", ignore = true)
    ProductOptionEntity toEntity(ProductOption option);

    @Mapping(target = "productId", source = "optionProduct.id")
    @Mapping(target = "name", source = "optionProduct.name")
    ProductOption toPojo(ProductOptionEntity entity);

    Product toPojoFromEditCommand(ProductEditCommand productEditCommand);

    ProductConfiguration toProductConfig(ProductConfigurationCommand productConfigurationCommand);

}
