package com.example.mapper;

import com.example.ProductOption;
import com.example.ProductOptionCommand;
import com.example.dto.ProductOptionDto;
import com.example.entity.ProductOptionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductOptionMapper {
    ProductOption toPojo(ProductOptionEntity productOptionEntity);
    ProductOptionEntity toEntity(ProductOption productOption);
    ProductOptionDto toDto(ProductOption productOption);
    ProductOption toPojoFromCommand(ProductOptionCommand productOptionCommand);
}
