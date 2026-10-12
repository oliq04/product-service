package com.example.mapper;

import com.example.ProductOption;
import com.example.ProductOptionCommand;
import com.example.dto.ProductOptionDto;
import com.example.entity.ProductOptionEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductOptionMapper {
    ProductOption toPojo(ProductOptionEntity productOptionEntity);
    ProductOptionEntity toEntity(ProductOption productOption);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "optionProduct", ignore = true)
    void updateEntity(ProductOption source, @MappingTarget ProductOptionEntity target);

    ProductOptionDto toDto(ProductOption productOption);
    ProductOption toPojoFromCommand(ProductOptionCommand productOptionCommand);
}
