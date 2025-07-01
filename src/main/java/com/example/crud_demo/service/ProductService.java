package com.example.crud_demo.service;

import com.example.crud_demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findAllProductsWithPages(int page, int size, String sortField);

    Page<Product> getProductsContainsFieldPages(int page, int size, String sortField, String containedStr);

    Page<Product> findAllProductsWithPages(Pageable pageable);
}
