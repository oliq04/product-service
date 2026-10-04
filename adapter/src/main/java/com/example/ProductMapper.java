package com.example;

import com.example.dto.ConfiguredProductDto;
import com.example.dto.ProductDto;
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

    @SubclassMapping(source = Smartphone.class, target = ConfiguredProductDto.class)
    @SubclassMapping(source = Computer.class, target = ConfiguredProductDto.class)
    ConfiguredProductDto toConfigured(Product product);

    ConfiguredProductDto toConfigured(Smartphone smartphone);

    ConfiguredProductDto toConfigured(Computer computer);

    @ObjectFactory
    default Product createProduct(ProductCommand command) {
        return createProduct(command.getType());
    }

    @ObjectFactory
    default Product createProduct(ProductEntity entity) {
        return createProduct(entity.getType());
    }

    default Product createProduct(String type) {
        return switch (type == null ? "" : type.toUpperCase()) {
            case "PHONE" -> new Smartphone();
            case "COMPUTER" -> new Computer();
            case "ELECTRONICS" -> new Electronics();
            default -> throw new IllegalArgumentException("Unsupported product type: " + type);
        };
    }

    @AfterMapping
    default void connectOptions(@MappingTarget ProductEntity product) {
        if (product.getOptions() != null) {
            product.getOptions().forEach(option -> option.setProduct(product));
        }
    }

    ProductConfiguration toProductConfig(ProductConfigurationCommand productConfigurationCommand);
}
