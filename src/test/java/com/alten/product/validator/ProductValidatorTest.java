package com.alten.product.validator;

import com.alten.product.dto.request.ProductRequestDto;
import com.alten.product.enums.InventoryStatus;
import com.alten.product.exception.ObjectNotValidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductValidatorTest {

    ObjectValidator<ProductRequestDto> productValidator = new ObjectValidator<>();

    ProductRequestDto product = new ProductRequestDto()
            .setCode("code")
            .setName("name")
            .setDescription("description")
            .setShellId(123456L)
            .setImage("image_url")
            .setPrice(1.0)
            .setCategory("category")
            .setQuantity(1)
            .setInventoryStatus(InventoryStatus.INSTOCK)
            .setRating(1);


    @Test
    @DisplayName("Should not throw exception when product is valid")
    void validate_product() {
        assertDoesNotThrow(() -> productValidator.validate(product));
    }

    @Test
    @DisplayName("Should throw exception when code is null")
    void validate_product_code() {
        product.setCode(null);
        assertThrows(ObjectNotValidException.class, () -> productValidator.validate(product));
    }

    @Test
    @DisplayName("Should throw exception when name is null")
    void validate_product_name() {
        product.setName(null);
        assertThrows(ObjectNotValidException.class, () -> productValidator.validate(product));
    }

    @Test
    @DisplayName("Should throw exception when description is null")
    void validate_product_description() {
        product.setDescription(null);
        assertThrows(ObjectNotValidException.class, () -> productValidator.validate(product));
    }

    @Test
    @DisplayName("Should throw exception when price is null")
    void validate_product_price() {
        product.setPrice(null);
        assertThrows(ObjectNotValidException.class, () -> productValidator.validate(product));
    }

    @Test
    @DisplayName("Should throw exception when category is null")
    void validate_product_category() {
        product.setCategory(null);
        assertThrows(ObjectNotValidException.class, () -> productValidator.validate(product));
    }

    @Test
    @DisplayName("Should throw exception when quantity is null")
    void validate_product_quantity() {
        product.setQuantity(null);
        assertThrows(ObjectNotValidException.class, () -> productValidator.validate(product));
    }

    @Test
    @DisplayName("Should throw exception when quantity is less than 0")
    void validate_product_quantity_value() {
        product.setQuantity(-5);
        assertThrows(ObjectNotValidException.class, () -> productValidator.validate(product));
    }

    @Test
    @DisplayName("Should throw exception when inventoryStatus is null")
    void validate_product_inventoryStatus() {
        product.setInventoryStatus(null);
        assertThrows(ObjectNotValidException.class, () -> productValidator.validate(product));
    }

    @Test
    @DisplayName("Should throw exception when rating is less than 0")
    void validate_product_rating_value() {
        product.setRating(-5);
        assertThrows(ObjectNotValidException.class, () -> productValidator.validate(product));
    }
}