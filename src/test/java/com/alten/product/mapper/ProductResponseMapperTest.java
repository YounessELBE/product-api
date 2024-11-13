package com.alten.product.mapper;

import com.alten.product.repository.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductResponseMapperTest {

    ProductResponseMapper productResponseMapper;

    @BeforeEach
    void setUp() {
        productResponseMapper = Mappers.getMapper(ProductResponseMapper.class);
    }

    @Test
    @DisplayName("Should map a product to a product response")
    void toResponse() {
        var product = new Product()
                .setId(1L)
                .setCode("code123")
                .setName("productName123")
                .setDescription("product description")
                .setPrice(100.0);

        var productResponseDto = productResponseMapper.toDto(product);

        assertNotNull(productResponseDto);
        assertEquals(product.getId(), productResponseDto.getId());
    }

}