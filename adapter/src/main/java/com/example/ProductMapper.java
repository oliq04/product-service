package com.example;

import com.example.dto.ConfiguredProductDto;
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

    @Mapping(target = "product", ignore = true)
    ProductOptionEntity toEntity(ProductOption option);

    ProductOption toPojo(ProductOptionEntity entity);

    Product toPojoFromEditCommand(ProductEditCommand productEditCommand);

    @AfterMapping
    default void connectOptions(@MappingTarget ProductEntity product) {
        if (product.getOptions() != null) {
            product.getOptions().forEach(option -> option.setProduct(product));
        }
    }

    ProductConfiguration toProductConfig(ProductConfigurationCommand productConfigurationCommand);
}
