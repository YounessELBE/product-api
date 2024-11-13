package com.alten.product.controller.api;

import com.alten.product.dto.request.ProductRequestDto;
import com.alten.product.dto.response.ErrorResponse;
import com.alten.product.dto.response.ProductResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Product")
public interface ProductApi {

    @Operation(
            summary = "Create a new product",
            description = "This method allows adding a new product.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Product Created",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    implementation = ProductResponseDto.class
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "302",
                            description = "Product already exist",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    implementation = ErrorResponse.class
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid object",
                            content = {
                                    @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = ErrorResponse.class
                                                    )
                                            )
                                    )
                            }
                    )
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto dto);

    @Operation(
            summary = "Retrieve all products .",
            description = "This method allows retrieving all products.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of all products / Empty list (if no products exist)"
                    )
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductResponseDto>> getAllProducts();

    @Operation(
            summary = "Retrieve details for product",
            description = "This method allows retrieving a product by ID.",
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH, name = "productId",
                            description = "ID of product to return"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The product has been found."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No product was found with the provided ID.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    implementation = ErrorResponse.class
                                            )
                                    )
                            }
                    )
            }
    )
    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponseDto> getProductById(@PathVariable("productId") Long id);

    @Operation(
            summary = "Update an existing product.",
            description = "Update details of a product if it exists.",
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "productId",
                            description = "Product id that need to be updated"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The product has been updated.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    implementation = ProductResponseDto.class
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid object.",
                            content = {
                                    @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = ErrorResponse.class
                                                    )
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No product was found with the provided ID.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    implementation = ErrorResponse.class
                                            )
                                    )
                            }
                    )
            }
    )
    @PatchMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponseDto> patchProduct(@PathVariable("productId") Long id,
                                                    @RequestBody ProductRequestDto requestDTO);

    @Operation(
            summary = "Remove a product",
            description = "This method allows removing a product",
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "productId",
                            description = "Product ID to delete"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "The product has been deleted."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No product was found with the provided ID.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    implementation = ErrorResponse.class
                                            )
                                    )
                            }
                    )
            }
    )
    @DeleteMapping(value = "/{productId}")
    ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long id);

}
