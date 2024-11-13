package com.alten.product.controller;

import com.alten.product.dto.request.ProductRequestDto;
import com.alten.product.dto.response.ProductResponseDto;
import com.alten.product.enums.InventoryStatus;
import com.alten.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
@ComponentScan(basePackages = "com.alten.product.messages")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String PRODUCTS_ENDPOINT = "/v1/products";


    @Test
    @DisplayName("Should create a product and return status CREATED")
    void createProduct_ShouldReturnCreatedStatus() throws Exception {
        ProductRequestDto requestDto = new ProductRequestDto()
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
        ProductResponseDto responseDto = new ProductResponseDto();

        when(productService.createProduct(any(ProductRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post(PRODUCTS_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    @DisplayName("Should retrieve all products and return status OK")
    void getAllProducts_ShouldReturnOkStatus() throws Exception {
        List<ProductResponseDto> products = Arrays.asList(new ProductResponseDto(), new ProductResponseDto());

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get(PRODUCTS_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(products)));
    }

    @Test
    @DisplayName("Should retrieve product by ID and return status OK")
    void getProductById_ShouldReturnOkStatus() throws Exception {
        Long productId = 1L;
        ProductResponseDto responseDto = new ProductResponseDto();

        when(productService.getProductById(productId)).thenReturn(responseDto);

        mockMvc.perform(get(PRODUCTS_ENDPOINT.concat("/{id}"), productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    @DisplayName("Should patch a product and return status OK")
    void patchProduct_ShouldReturnOkStatus() throws Exception {
        Long productId = 1L;
        ProductRequestDto requestDto = new ProductRequestDto();
        ProductResponseDto responseDto = new ProductResponseDto();

        when(productService.patchProduct(eq(productId), any(ProductRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(patch(PRODUCTS_ENDPOINT.concat("/{id}"), productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    @DisplayName("Should delete a product and return status NO_CONTENT")
    void deleteProduct_ShouldReturnNoContentStatus() throws Exception {
        Long productId = 1L;

        Mockito.doNothing().when(productService).deleteProduct(productId);

        mockMvc.perform(MockMvcRequestBuilders.delete(PRODUCTS_ENDPOINT.concat("/{id}"), productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
