package com.example.crud_demo.controller;

import com.example.crud_demo.model.InterviewTable;
import com.example.crud_demo.model.Product;
import com.example.crud_demo.repository.InterviewRepository;
import com.example.crud_demo.repository.ProductRepository;
import com.example.crud_demo.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
public class CrudController {
    @Resource
    private InterviewRepository interviewRepository;

    @Resource
    private ProductRepository productRepository;

    @Resource
    private ProductService productService;


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/interview")
    public ResponseEntity<List<InterviewTable>> sayInterview() {
        List<InterviewTable> interviewTables = interviewRepository.findAll();
        return ResponseEntity.ok(interviewTables);
    }


    @GetMapping("/join/{id}")
    public ResponseEntity<List<Object>> sayJoin(@PathVariable("id") Integer id) {
        List<Object> interviewArticleByNativeSQL = interviewRepository.findInterviewArticleByNativeSQL(id);
        return ResponseEntity.ok(interviewArticleByNativeSQL);
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> sayProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/pages")
    public ResponseEntity<Page<Product>> sayProductsPage(@PageableDefault(page = 0, size=10,sort="id")Pageable pageable) {
        Page<Product> allProductsWithPages = this.productService.findAllProductsWithPages(pageable);
        return ResponseEntity.ok(allProductsWithPages);
    }
}
