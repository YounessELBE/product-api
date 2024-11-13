package com.alten.product.service;

import com.alten.product.dto.request.ProductRequestDto;
import com.alten.product.dto.response.ProductResponseDto;
import com.alten.product.exception.BusinessException;
import com.alten.product.mapper.ProductRequestMapper;
import com.alten.product.mapper.ProductResponseMapper;
import com.alten.product.messages.ProductMessages;
import com.alten.product.repository.ProductRepository;
import com.alten.product.repository.entity.Product;
import com.alten.product.service.Impl.ProductServiceImpl;
import com.alten.product.validator.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductRequestMapper productRequestMapper;

    @Mock
    private ProductResponseMapper productResponseMapper;

    @Mock
    private ObjectValidator<ProductRequestDto> productValidator;

    @Mock
    private ProductMessages messages;

    @InjectMocks
    private ProductServiceImpl productService;


    @Test
    @DisplayName("Should create a new product")
    void createProduct_ShouldCreateNewProduct() {
        // Given
        ProductRequestDto productRequestDto = new ProductRequestDto();
        Product product = new Product();
        Product savedProduct = new Product();
        ProductResponseDto responseDto = new ProductResponseDto();

        when(productRepository.findByCode(productRequestDto.getCode())).thenReturn(Optional.empty());
        when(productRequestMapper.toEntity(productRequestDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(savedProduct);
        when(productResponseMapper.toDto(savedProduct)).thenReturn(responseDto);

        // When
        ProductResponseDto result = productService.createProduct(productRequestDto);

        // Then
        assertNotNull(result);
        assertEquals(responseDto, result);
        verify(productRepository).save(product);
    }

    @Test
    @DisplayName("Should Throw Exception When Code Already Exists")
    void createProduct_ShouldThrowException_WhenCodeAlreadyExists() {
        // Given
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setCode("product123");
        Product existingProduct = new Product();

        when(productRepository.findByCode(productRequestDto.getCode())).thenReturn(Optional.of(existingProduct));
        when(messages.getMessage("PRODUCT_CODE_ALREADY_EXISTS", existingProduct.getCode()))
                .thenReturn("Product code already exists");

        // When & Then
        BusinessException exception = assertThrows(BusinessException.class,
                () -> productService.createProduct(productRequestDto));

        assertEquals("Product code already exists", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Test
    @DisplayName("Should Return All Products")
    void getAllProducts_ShouldReturnAllProducts() {
        // Given
        Product product = new Product();
        ProductResponseDto responseDto = new ProductResponseDto();

        when(productRepository.findAll()).thenReturn(Arrays.asList(product));
        when(productResponseMapper.toDto(product)).thenReturn(responseDto);

        // When
        List<ProductResponseDto> result = productService.getAllProducts();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDto, result.get(0));
    }

    @Test
    @DisplayName("Should Return Product by ID")
    void getProductById_ShouldReturnProduct() {
        // Given
        Long id = 1L;
        Product product = new Product();
        ProductResponseDto responseDto = new ProductResponseDto();

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productResponseMapper.toDto(product)).thenReturn(responseDto);

        // When
        ProductResponseDto result = productService.getProductById(id);

        // Then
        assertNotNull(result);
        assertEquals(responseDto, result);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when Product not found")
    void getProductById_ShouldThrowEntityNotFoundException_WhenProductNotFound() {
        // Given
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());
        when(messages.getMessage("PRODUCT_NOT_FOUND_BY_ID", id)).thenReturn("Product not found");

        // When & Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> productService.getProductById(id));
        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should patch existing product")
    void patchProduct_ShouldPatchExistingProduct() {
        // Given
        Long id = 1L;
        ProductRequestDto productDto = new ProductRequestDto();
        Product existingProduct = new Product();
        ProductResponseDto responseDto = new ProductResponseDto();

        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        when(productRepository.findByCodeAndIdNot(productDto.getCode(), id)).thenReturn(Optional.empty());
        when(productRequestMapper.toDto(existingProduct)).thenReturn(productDto);
        when(productResponseMapper.toDto(existingProduct)).thenReturn(responseDto);
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        // When
        ProductResponseDto result = productService.patchProduct(id, productDto);

        // Then
        assertNotNull(result);
        assertEquals(responseDto, result);
        verify(productRequestMapper).updateProductFromDto(productDto, existingProduct);
        verify(productValidator).validate(productDto);
    }

    @Test
    @DisplayName("Should delete Product")
    void deleteProduct_ShouldDeleteProduct() {
        // Given
        Long id = 1L;
        Product product = new Product();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // When
        productService.deleteProduct(id);

        // Then
        verify(productRepository).delete(product);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when product not found")
    void deleteProduct_ShouldThrowEntityNotFoundException_WhenProductNotFound() {
        // Given
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());
        when(messages.getMessage("PRODUCT_NOT_FOUND_BY_ID", id)).thenReturn("Product not found");

        // When & Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> productService.deleteProduct(id));
        assertEquals("Product not found", exception.getMessage());
    }

}