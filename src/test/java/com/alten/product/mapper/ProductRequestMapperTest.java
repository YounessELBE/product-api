package com.alten.product.mapper;

import com.alten.product.dto.request.ProductRequestDto;
import com.alten.product.repository.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductRequestMapperTest {

    ProductRequestMapper productRequestMapper;

    @BeforeEach
    void setUp() {
        productRequestMapper = Mappers.getMapper(ProductRequestMapper.class);
    }

    @Test
    @DisplayName("Should map a product request to a product entity")
    void toEntity() {
        var productRequest = new ProductRequestDto()
                .setCode("code123")
                .setName("productName123")
                .setDescription("product description")
                .setPrice(100.0);

        var product = productRequestMapper.toEntity(productRequest);

        assertEquals(productRequest.getCode(), product.getCode());
        assertEquals(productRequest.getName(), product.getName());
        assertEquals(productRequest.getPrice(), product.getPrice());
        assertEquals(productRequest.getDescription(), product.getDescription());
    }

    @Test
    void testUpdateProductFromDto() {
        // Given
        Product product = new Product();
        product.setCode("code123");
        product.setName("Old Product Name");
        product.setDescription("Old Product Description");

        ProductRequestDto dto = new ProductRequestDto();
        dto.setName("Updated Product Name");
        dto.setDescription("Updated Product Description");

        // When
        productRequestMapper.updateProductFromDto(dto, product);

        // Then
        assertEquals("code123", product.getCode());
        assertEquals("Updated Product Name", product.getName());
        assertEquals("Updated Product Description", product.getDescription());
    }
}