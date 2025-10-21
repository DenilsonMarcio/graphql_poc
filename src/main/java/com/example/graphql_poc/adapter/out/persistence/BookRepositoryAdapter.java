package com.example.graphql_poc.adapter.out.persistence;

import com.example.graphql_poc.domain.model.Book;
import com.example.graphql_poc.domain.port.BookRepositoryPort;
import com.example.graphql_poc.model.BookEntity;
import com.example.graphql_poc.repository.BookRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Repository
public class BookRepositoryAdapter implements BookRepositoryPort {

    private final BookRepository bookRepository;

    public BookRepositoryAdapter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        BookEntity entity = BookEntityMapper.toEntity(book);
        BookEntity saved = bookRepository.save(entity);
        return BookEntityMapper.toDomain(saved);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id).map(BookEntityMapper::toDomain);
    }

    @Override
    public boolean deleteById(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
