package com.msfb.productrestapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msfb.productrestapi.dto.request.ProductRequest;
import com.msfb.productrestapi.dto.request.UpdateProductRequest;
import com.msfb.productrestapi.dto.response.CommonResponse;
import com.msfb.productrestapi.dto.response.ProductResponse;
import com.msfb.productrestapi.entity.Product;
import com.msfb.productrestapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Product Api", description = "Product API")
public class ProductController {

    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @Operation(
            summary = "Create new product",
            description = "Create new product"
    )
    @SecurityRequirement(name = "Authorization")
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<?>> create(
            @RequestPart(name = "product") String jsonProduct,
            @RequestPart(name = "image", required = false) MultipartFile image
    ) {
        CommonResponse.CommonResponseBuilder<Product> builder = CommonResponse.builder();
        try {
            ProductRequest request = objectMapper.readValue(jsonProduct, new TypeReference<>() {
            });
            request.setImage(image);

            Product response = productService.create(request);
            builder.statusCode(HttpStatus.CREATED.value());
            builder.message("Created Data Successfully");
            builder.data(response);
            return new ResponseEntity<>(builder.build(), HttpStatus.CREATED);
        } catch (Exception e) {
            builder.statusCode(HttpStatus.BAD_REQUEST.value());
            builder.message(e.getMessage());
            return new ResponseEntity<>(builder.build(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Get all product",
            description = "Get all product"
    )
    @SecurityRequirement(name = "Authorization")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<List<ProductResponse>>> getAll(
            @RequestParam(name = "sortBy", defaultValue = "createdAt") String sort,
            @RequestParam(name = "direction", defaultValue = "desc") String direction
    ) {
        List<ProductResponse> result = productService.findAll(sort,direction);
        CommonResponse<List<ProductResponse>> response = CommonResponse.<List<ProductResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all data successfully")
                .data(result)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Get product by id",
            description = "Get product by id"
    )
    @SecurityRequirement(name = "Authorization")
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<ProductResponse>> findById(@PathVariable String id) {
        ProductResponse result = productService.findProductById(id);
        CommonResponse<ProductResponse> response = CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get data successfully")
                .data(result)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Update product",
            description = "Update product"
    )
    @SecurityRequirement(name = "Authorization")
    @PutMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<?>> update(
            @RequestPart(name = "product") String jsonProduct,
            @RequestPart(name = "image", required = false) MultipartFile image
    ) {
        CommonResponse.CommonResponseBuilder<Product> builder = CommonResponse.builder();
        try {
            UpdateProductRequest request = objectMapper.readValue(jsonProduct, new TypeReference<>() {
            });
            request.setImage(image);

            Product response = productService.update(request);
            builder.statusCode(HttpStatus.CREATED.value());
            builder.message("Updated data successfully");
            builder.data(response);
            return new ResponseEntity<>(builder.build(), HttpStatus.CREATED);
        } catch (Exception e) {
            builder.statusCode(HttpStatus.BAD_REQUEST.value());
            builder.message(e.getMessage());
            return new ResponseEntity<>(builder.build(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Delete product",
            description = "Delete product"
    )
    @SecurityRequirement(name = "Authorization")
    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<?>> delete(@PathVariable String id) {
        CommonResponse.CommonResponseBuilder<Product> builder = CommonResponse.builder();
        try {
            productService.delete(id);
            builder.statusCode(HttpStatus.OK.value());
            builder.message("Deleted data successfully");
            return new ResponseEntity<>(builder.build(), HttpStatus.OK);
        } catch (Exception e) {
            builder.statusCode(HttpStatus.BAD_REQUEST.value());
            builder.message(e.getMessage());
            return new ResponseEntity<>(builder.build(), HttpStatus.BAD_REQUEST);
        }
    }
}
