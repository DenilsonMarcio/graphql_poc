package com.example.graphql_poc.controller;

import com.example.graphql_poc.model.Book;
import com.example.graphql_poc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

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
        Book newBook = new Book(null, book.getTitle(), book.getAuthor(), book.getPublicationYear());
        return bookService.saveBook(newBook);
    }

    @MutationMapping
    public Book updateBook(@Argument Long id, @Argument BookInput book) {
        return bookService.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setPublicationYear(book.getPublicationYear());
                    return bookService.saveBook(existingBook);
                })
                .orElse(null);
    }

    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        return bookService.deleteBook(id);
    }

    // Classe auxiliar para Input Type (se não usar Record/DTO)
    public static class BookInput {
        private String title;
        private String author;
        private Integer publicationYear;
        // Getters e Setters (ou use Lombok @Data se preferir)
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }
        public Integer getPublicationYear() { return publicationYear; }
        public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }
    }
}