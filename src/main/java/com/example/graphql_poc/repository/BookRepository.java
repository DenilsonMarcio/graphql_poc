package com.example.graphql_poc.repository;


import com.example.graphql_poc.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    // Métodos CRUD básicos fornecidos pelo JpaRepository
}