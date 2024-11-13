package com.alten.product.mapper;

import com.alten.product.dto.response.ProductResponseDto;
import com.alten.product.repository.entity.Product;
import org.mapstruct.Mapper;

import java.time.Instant;

@Mapper
public interface ProductResponseMapper {
    Product toEntity(ProductResponseDto dto);
    ProductResponseDto toDto(Product entity);

}
