package com.example.graphql_poc.repository;


import com.example.graphql_poc.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Métodos CRUD básicos fornecidos pelo JpaRepository
}