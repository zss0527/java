package com.example.crud_demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@IdClass(InterviewCompositeKey.class)
@Table(name = "interview_table")
public class InterviewTable {
    @Id
    private int id;

    @Id
    private String id2;

    private String field1;
    private String field2;
    private Integer field3;
    private LocalDateTime updateTime;
}
