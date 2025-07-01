package com.example.crud_demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="article")
@Entity
public class Article {
    @Id
    private Integer id;

    private String title;

    private String content;
}
