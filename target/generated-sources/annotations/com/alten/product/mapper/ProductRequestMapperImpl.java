package com.alten.product.mapper;

import com.alten.product.dto.request.ProductRequestDto;
import com.alten.product.repository.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.1 (Eclipse Adoptium)"
)
@Component
public class ProductRequestMapperImpl implements ProductRequestMapper {

    @Override
    public Product toEntity(ProductRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setCode( dto.getCode() );
        product.setName( dto.getName() );
        product.setDescription( dto.getDescription() );
        product.setImage( dto.getImage() );
        product.setCategory( dto.getCategory() );
        product.setPrice( dto.getPrice() );
        product.setQuantity( dto.getQuantity() );
        product.setShellId( dto.getShellId() );
        product.setInventoryStatus( dto.getInventoryStatus() );
        product.setRating( dto.getRating() );

        return product;
    }

    @Override
    public ProductRequestDto toDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductRequestDto productRequestDto = new ProductRequestDto();

        productRequestDto.setCode( entity.getCode() );
        productRequestDto.setName( entity.getName() );
        productRequestDto.setDescription( entity.getDescription() );
        productRequestDto.setPrice( entity.getPrice() );
        productRequestDto.setQuantity( entity.getQuantity() );
        productRequestDto.setShellId( entity.getShellId() );
        productRequestDto.setInventoryStatus( entity.getInventoryStatus() );
        productRequestDto.setCategory( entity.getCategory() );
        productRequestDto.setImage( entity.getImage() );
        productRequestDto.setRating( entity.getRating() );

        return productRequestDto;
    }

    @Override
    public void updateProductFromDto(ProductRequestDto productDTO, Product product) {
        if ( productDTO == null ) {
            return;
        }

        if ( productDTO.getCode() != null ) {
            product.setCode( productDTO.getCode() );
        }
        if ( productDTO.getName() != null ) {
            product.setName( productDTO.getName() );
        }
        if ( productDTO.getDescription() != null ) {
            product.setDescription( productDTO.getDescription() );
        }
        if ( productDTO.getImage() != null ) {
            product.setImage( productDTO.getImage() );
        }
        if ( productDTO.getCategory() != null ) {
            product.setCategory( productDTO.getCategory() );
        }
        if ( productDTO.getPrice() != null ) {
            product.setPrice( productDTO.getPrice() );
        }
        if ( productDTO.getQuantity() != null ) {
            product.setQuantity( productDTO.getQuantity() );
        }
        if ( productDTO.getShellId() != null ) {
            product.setShellId( productDTO.getShellId() );
        }
        if ( productDTO.getInventoryStatus() != null ) {
            product.setInventoryStatus( productDTO.getInventoryStatus() );
        }
        if ( productDTO.getRating() != null ) {
            product.setRating( productDTO.getRating() );
        }
    }
}
