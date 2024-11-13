package com.alten.product.mapper;

import com.alten.product.dto.request.ProductRequestDto;
import com.alten.product.repository.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductRequestMapper {

    Product toEntity(ProductRequestDto dto);
    ProductRequestDto toDto(Product entity);
    void updateProductFromDto(ProductRequestDto productDTO, @MappingTarget Product product);

}

