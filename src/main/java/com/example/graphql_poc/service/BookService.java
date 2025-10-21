package com.example.graphql_poc.service;


import com.example.graphql_poc.domain.model.Book;
import com.example.graphql_poc.domain.port.BookRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepositoryPort bookRepository;

    public BookService(BookRepositoryPort bookRepository) {
        this.bookRepository = bookRepository;
    }

    // CREATE e UPDATE
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // READ All
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // READ by ID
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    // DELETE
    public boolean deleteBook(Long id) {
        return bookRepository.deleteById(id);
    }
}