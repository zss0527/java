package com.example.crud_demo.service.impl;

import com.example.crud_demo.model.Product;
import com.example.crud_demo.repository.ProductRepository;
import com.example.crud_demo.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductRepository productRepository;


    public Page<Product> findAllProductsWithPages(int page, int size, String sortField) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortField));

        return productRepository.findAll(pageable);
    }

    @Retryable(
            retryFor = {RuntimeException.class},
            noRetryFor = {IOException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 7000)
    )
    public Page<Product> getProductsContainsFieldPages(int page, int size, String sortField, String containedStr) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortField));
        return productRepository.findByNameContaining(containedStr, pageable);
    }

    @Retryable(
            retryFor = {RuntimeException.class},
            noRetryFor = {IOException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 7000)
    )
    public Page<Product> findAllProductsWithPages(Pageable pageable) {

        return productRepository.findAll(pageable);
    }

    @Recover
    public String recover(Exception e, int id) {
        System.out.println(e.getMessage());
        System.out.println(id);
        return "default value";
    }
}
