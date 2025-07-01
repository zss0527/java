package com.example.crud_demo.repository;

import com.example.crud_demo.model.InterviewCompositeKey;
import com.example.crud_demo.model.InterviewTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterviewRepository extends JpaRepository<InterviewTable, InterviewCompositeKey> {
    @Query(value = "select itt.id, itt.field1, a.content from interview_table itt join article a on itt.id = a.id where itt.id = :id", nativeQuery = true)
    List<Object> findInterviewArticleByNativeSQL(@Param("id") int id);
}
