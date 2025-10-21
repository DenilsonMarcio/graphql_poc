package com.example.graphql_poc.service;


import com.example.graphql_poc.model.Book;
import com.example.graphql_poc.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

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
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}