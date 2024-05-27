package com.msfb.productrestapi.service.impl;

import com.msfb.productrestapi.dto.request.ProductRequest;
import com.msfb.productrestapi.dto.request.UpdateProductRequest;
import com.msfb.productrestapi.entity.Image;
import com.msfb.productrestapi.entity.Product;
import com.msfb.productrestapi.repository.ProductRepository;
import com.msfb.productrestapi.service.ImageService;
import com.msfb.productrestapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (request.getImage().isEmpty()) throw new RuntimeException("image is required");

        Image image = imageService.create(request.getImage());
        Product payload = Product.builder()
                .skuCode(request.getSkuCode())
                .productName(request.getProductName())
                .qty(request.getQty())
                .price(request.getPrice())
                .image(image)
                .build();
        return repository.saveAndFlush(payload);
    }

    @Transactional(readOnly = true)
    @Override
    public Product findById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return repository.findAll();
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
}
