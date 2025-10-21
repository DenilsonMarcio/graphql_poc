package com.example.graphql_poc.controller;

import com.example.graphql_poc.domain.model.Book;
import com.example.graphql_poc.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // --- QUERIES (READ) ---

    @QueryMapping
    public List<Book> findAllBooks() {
        return bookService.findAll();
    }

    @QueryMapping
    public Book findBookById(@Argument Long id) {
        return bookService.findById(id).orElse(null);
    }

    // --- MUTATIONS (CREATE, UPDATE, DELETE) ---

    @MutationMapping
    public Book createBook(@Argument BookInput book) {
        Book newBook = new Book(null, book.title(), book.author(), book.publicationYear());
        return bookService.saveBook(newBook);
    }

    @MutationMapping
    public Book updateBook(@Argument Long id, @Argument BookInput book) {
        return bookService.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(book.title());
                    existingBook.setAuthor(book.author());
                    existingBook.setPublicationYear(book.publicationYear());
                    return bookService.saveBook(existingBook);
                })
                .orElse(null);
    }

    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        return bookService.deleteBook(id);
    }

    // Input record (clean, immutable)
    public static record BookInput(String title, String author, Integer publicationYear) { }
}