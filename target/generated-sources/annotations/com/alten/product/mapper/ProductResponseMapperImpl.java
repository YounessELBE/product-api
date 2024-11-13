package com.alten.product.mapper;

import com.alten.product.dto.response.ProductResponseDto;
import com.alten.product.repository.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.1 (Eclipse Adoptium)"
)
@Component
public class ProductResponseMapperImpl implements ProductResponseMapper {

    @Override
    public Product toEntity(ProductResponseDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setCreatedAt( dto.getCreatedAt() );
        product.setUpdatedAt( dto.getUpdatedAt() );
        product.setId( dto.getId() );
        product.setCode( dto.getCode() );
        product.setName( dto.getName() );
        product.setDescription( dto.getDescription() );
        product.setImage( dto.getImage() );
        product.setCategory( dto.getCategory() );
        product.setPrice( dto.getPrice() );
        product.setQuantity( dto.getQuantity() );
        product.setInternalReference( dto.getInternalReference() );
        product.setShellId( dto.getShellId() );
        product.setInventoryStatus( dto.getInventoryStatus() );
        product.setRating( dto.getRating() );

        return product;
    }

    @Override
    public ProductResponseDto toDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId( entity.getId() );
        productResponseDto.setCode( entity.getCode() );
        productResponseDto.setName( entity.getName() );
        productResponseDto.setDescription( entity.getDescription() );
        productResponseDto.setImage( entity.getImage() );
        productResponseDto.setCategory( entity.getCategory() );
        productResponseDto.setPrice( entity.getPrice() );
        productResponseDto.setQuantity( entity.getQuantity() );
        productResponseDto.setInternalReference( entity.getInternalReference() );
        productResponseDto.setShellId( entity.getShellId() );
        productResponseDto.setInventoryStatus( entity.getInventoryStatus() );
        productResponseDto.setRating( entity.getRating() );
        productResponseDto.setCreatedAt( entity.getCreatedAt() );
        productResponseDto.setUpdatedAt( entity.getUpdatedAt() );

        return productResponseDto;
    }
}
