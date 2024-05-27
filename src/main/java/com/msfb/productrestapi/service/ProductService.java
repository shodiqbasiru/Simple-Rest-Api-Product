package com.msfb.productrestapi.service;

import com.msfb.productrestapi.dto.request.ProductRequest;
import com.msfb.productrestapi.dto.request.UpdateProductRequest;
import com.msfb.productrestapi.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(ProductRequest request);
    Product findById(String id);
    List<Product> findAll();
    Product update(UpdateProductRequest request);
    void delete(String id);
}
