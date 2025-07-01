package com.example.crud_demo.repository;

import com.example.crud_demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
//    Page<Product> findAll(Pageable pageable);


    @Query("select p from Product p where p.name like %?1%")
    Page<Product> findByNameContaining(String name, Pageable pageable);

}
