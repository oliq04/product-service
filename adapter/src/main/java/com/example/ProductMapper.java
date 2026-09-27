package com.example;

import com.example.entity.ProductEntity;
import org.mapstruct.Mapper;
import com.example.entity.CompConfig;
import com.example.entity.PhoneConfig;

import java.util.Locale;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    default Product toPojo(ProductCommand command) {
        if (command == null) {
            return null;
        }

        Product product = newProduct(command.getType());
        copyCommonFields(null, command.getName(), command.getPrice(), command.getType(),
                command.getQuantity(), product);

        if (product instanceof Computer computer) {
            computer.setRam(command.getRam());
            computer.setProcessor(command.getProcessor());
            computer.setStorage(command.getStorage());
        } else if (product instanceof Smartphone smartphone) {
            smartphone.setBattery(command.getBattery());
            smartphone.setColor(command.getScreenSize());
        }

        return product;
    }

    default ProductDto toDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setType(product.getType());
        dto.setQuantity(product.getQuantity());

        if (product instanceof Computer computer) {
            dto.setRam(computer.getRam());
            dto.setProcessor(computer.getProcessor());
            dto.setStorage(computer.getStorage());
        } else if (product instanceof Smartphone smartphone) {
            dto.setBattery(smartphone.getBattery());
            dto.setColor(smartphone.getColor());
        }

        return dto;
    }

    default ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }

        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setType(product.getType());
        entity.setQuantity(product.getQuantity());

        if (product instanceof Computer computer) {
            CompConfig config = new CompConfig();
            config.setRam(computer.getRam());
            config.setProcessor(computer.getProcessor());
            config.setStorage(computer.getStorage());
            entity.setConfiguration(config);
        } else if (product instanceof Smartphone smartphone) {
            PhoneConfig config = new PhoneConfig();
            config.setBattery(smartphone.getBattery());
            config.setColor(smartphone.getColor());
            entity.setConfiguration(config);
        }

        return entity;
    }

    default Product toPojoFromEntity(ProductEntity entity) {
        if (entity == null) {
            return null;
        }

        Product product = newProduct(entity.getType());
        copyCommonFields(entity.getId(), entity.getName(), entity.getPrice(), entity.getType(),
                entity.getQuantity(), product);

        if (product instanceof Computer computer && entity.getConfiguration() instanceof CompConfig config) {
            computer.setRam(config.getRam());
            computer.setProcessor(config.getProcessor());
            computer.setStorage(config.getStorage());
        } else if (product instanceof Smartphone smartphone
                && entity.getConfiguration() instanceof PhoneConfig config) {
            smartphone.setBattery(config.getBattery());
            smartphone.setColor(config.getColor());
        }

        return product;
    }

    private static Product newProduct(String type) {
        String normalizedType = type == null ? null : type.toUpperCase(Locale.ROOT);
        if (normalizedType == null) {
            throw new IllegalArgumentException("Product type is required");
        }

        return switch (normalizedType) {
            case "PHONE" -> new Smartphone();
            case "ELECTRONICS" -> new Electronics();
            case "COMPUTER" -> new Computer();
            default -> throw new IllegalArgumentException("Unsupported product type: " + type);
        };
    }

    private static void copyCommonFields(Long id, String name, java.math.BigDecimal price, String type,
                                         Long quantity, Product product) {
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setType(type == null ? null : type.toUpperCase(Locale.ROOT));
        product.setQuantity(quantity);
    }
}
