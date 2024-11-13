package com.alten.product.service;

import com.alten.product.dto.request.ProductRequestDto;
import com.alten.product.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto requestDto);
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto getProductById(Long id);
    ProductResponseDto patchProduct(Long id, ProductRequestDto productDTO);
    void deleteProduct(Long id);
}
