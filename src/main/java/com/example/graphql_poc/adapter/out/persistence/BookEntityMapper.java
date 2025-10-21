package com.example.graphql_poc.adapter.out.persistence;

import com.example.graphql_poc.domain.model.Book;
import com.example.graphql_poc.model.BookEntity;

public final class BookEntityMapper {

    private BookEntityMapper() { }

    public static BookEntity toEntity(Book domain) {
        if (domain == null) return null;
        return new BookEntity(
                domain.getId(),
                domain.getTitle(),
                domain.getAuthor(),
                domain.getPublicationYear()
        );
    }

    public static Book toDomain(BookEntity entity) {
        if (entity == null) return null;
        return new Book(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getPublicationYear()
        );
    }
}

