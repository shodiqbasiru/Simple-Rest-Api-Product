package com.msfb.productrestapi.service;

import com.msfb.productrestapi.dto.request.ProductRequest;
import com.msfb.productrestapi.dto.request.UpdateProductRequest;
import com.msfb.productrestapi.dto.response.ProductResponse;
import com.msfb.productrestapi.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(ProductRequest request);
    Product findById(String id);
    ProductResponse findProductById(String id);
    List<ProductResponse> findAll(String sort,String direction);
    Product update(UpdateProductRequest request);
    void delete(String id);
}
