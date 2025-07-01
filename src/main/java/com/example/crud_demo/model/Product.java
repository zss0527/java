package com.example.crud_demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    private int id;

    private String description;
    private String name;
    private double price;
    private boolean status;
}
