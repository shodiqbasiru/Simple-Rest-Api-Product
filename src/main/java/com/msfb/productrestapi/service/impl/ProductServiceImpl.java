package com.msfb.productrestapi.service.impl;

import com.msfb.productrestapi.dto.request.ProductRequest;
import com.msfb.productrestapi.dto.request.UpdateProductRequest;
import com.msfb.productrestapi.dto.response.ImageResponse;
import com.msfb.productrestapi.dto.response.ProductResponse;
import com.msfb.productrestapi.entity.Image;
import com.msfb.productrestapi.entity.Product;
import com.msfb.productrestapi.repository.ProductRepository;
import com.msfb.productrestapi.service.ImageService;
import com.msfb.productrestapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ImageService imageService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Product create(ProductRequest request) {
        if (request.getImage().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "image is required");

        Image image = imageService.create(request.getImage());
        Product payload = Product.builder()
                .skuCode(request.getSkuCode())
                .productName(request.getProductName())
                .qty(request.getQty())
                .price(request.getPrice())
                .image(image)
                .createdAt(new Date())
                .build();
        return repository.saveAndFlush(payload);
    }

    @Override
    public Product findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"product not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponse findProductById(String id) {
        Product product = findById(id);
        return getProductResponse(product);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponse> findAll(String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction),sortBy);
        return repository.findAll(sort).stream()
                .map(this::getProductResponse)
                .toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Product update(UpdateProductRequest request) {
        Product currentProduct = findById(request.getId());
        Image oldImage = currentProduct.getImage();

        currentProduct.setSkuCode(request.getSkuCode());
        currentProduct.setProductName(request.getProductName());
        currentProduct.setPrice(request.getPrice());
        currentProduct.setQty(request.getQty());
        currentProduct.setCreatedAt(new Date());
        if (request.getImage() != null) {
            Image newImage = imageService.create(request.getImage());
            currentProduct.setImage(newImage);
            if (oldImage != null) {
                imageService.deleteById(oldImage.getId());
            }
        }
        currentProduct = repository.saveAndFlush(currentProduct);
        return currentProduct;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        Product currentProduct = findById(id);
        repository.delete(currentProduct);
    }

    private ProductResponse getProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .skuCode(product.getSkuCode())
                .productName(product.getProductName())
                .qty(product.getQty())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .image(ImageResponse.builder()
                        .url("/api/products/image/" + product.getImage().getId())
                        .name(product.getImage().getName())
                        .build())
                .build();
    }
}
