package com.example.graphql_poc.domain.port;

import com.example.graphql_poc.domain.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryPort {
    Book save(Book book);
    List<Book> findAll();
    Optional<Book> findById(Long id);
    boolean deleteById(Long id);
}

