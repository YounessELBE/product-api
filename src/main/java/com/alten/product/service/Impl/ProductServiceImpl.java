package com.alten.product.service.Impl;

import com.alten.product.dto.request.ProductRequestDto;
import com.alten.product.dto.response.ProductResponseDto;
import com.alten.product.exception.BusinessException;
import com.alten.product.mapper.ProductRequestMapper;
import com.alten.product.mapper.ProductResponseMapper;
import com.alten.product.messages.ProductMessages;
import com.alten.product.repository.ProductRepository;
import com.alten.product.repository.entity.Product;
import com.alten.product.service.ProductService;
import com.alten.product.validator.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductRequestMapper productRequestMapper;
    private final ProductResponseMapper productResponseMapper;
    private final ObjectValidator<ProductRequestDto> productValidator;
    private final ProductMessages messages;


    /**
     * Create a new product.
     * @param productDto {@link ProductRequestDto} containing information for the new product.
     *
     * @return {@link ProductResponseDto} representing the created product.
     */
    @Override
    @Transactional
    public ProductResponseDto createProduct(final ProductRequestDto productDto) {
        productRepository.findByCode(productDto.getCode()).ifPresent(product -> {
            throw new BusinessException(messages.getMessage("PRODUCT_CODE_ALREADY_EXISTS", product.getCode()), HttpStatus.BAD_REQUEST);
        });

        final Product product = productRequestMapper.toEntity(productDto);
        final String internalReference = generateInternalReference();
        product.setInternalReference(internalReference);

        final Product savedProduct = productRepository.save(product);
        return productResponseMapper.toDto(savedProduct);
    }

    /**
     * Retrieve all products.
     *
     * @return List of {@link ProductResponseDto} representing all products.
     */
    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a product by ID.
     * @param id The ID of the product to retrieve.
     *
     * @return {@link ProductResponseDto} representing the retrieved product.
     * @throws EntityNotFoundException if the product with the specified ID is not found.
     */
    @Override
    public ProductResponseDto getProductById(final Long id) {
        Product product = getProductOrThrowEntityNotFound(id);
        return productResponseMapper.toDto(product);
    }

    /**
     * Updates a product's information based on the given partial data in the request.
     *
     * @param id     The ID of the product to be updated.
     * @param productDto the DTO containing the product fields to be updated.
     *
     * @return A {@link ProductResponseDto} representing the updated product.
     * @throws EntityNotFoundException if no product is found with the given ID
     * @throws BusinessException if the provided product code already exists
     */
    @Override
    @Transactional
    public ProductResponseDto patchProduct(final Long id, final ProductRequestDto productDto) {

        Product existingProduct = getProductOrThrowEntityNotFound(id);

        productRepository.findByCodeAndIdNot(productDto.getCode(), id).ifPresent(product -> {
            throw new BusinessException(messages.getMessage("PRODUCT_CODE_ALREADY_EXISTS", productDto.getCode()), HttpStatus.BAD_REQUEST);
        });

        productRequestMapper.updateProductFromDto(productDto, existingProduct);
        productValidator.validate(productRequestMapper.toDto(existingProduct));

        Product updatedProduct = productRepository.save(existingProduct);
        return productResponseMapper.toDto(updatedProduct);
    }

    /**
     * Delete a product by ID.
     *
     * @param id The ID of the product to delete.
     * @throws EntityNotFoundException if the product with the specified ID is not found.
     */
    @Override
    @Transactional
    public void deleteProduct(final Long id) {
        Product product = getProductOrThrowEntityNotFound(id);
        productRepository.delete(product);
    }

    /**
     * Generate Internal Reference
     *
     * @return a new generated Internal Reference
     */
    private String generateInternalReference() {
        return UUID.randomUUID().toString();
    }

    /**
     * Helper method to retrieve a product by ID or throw an exception if not found.
     * @param id The ID of the product to retrieve.
     * @return Product representing the retrieved product.
     * @throws EntityNotFoundException if the product with the specified ID is not found.
     */
    private Product getProductOrThrowEntityNotFound(final Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messages.getMessage("PRODUCT_NOT_FOUND_BY_ID", id))); // TODO use ResourceNotFoundException
    }
}

