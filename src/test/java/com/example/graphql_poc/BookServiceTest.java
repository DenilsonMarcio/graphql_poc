package com.example.graphql_poc;

import com.example.graphql_poc.model.Book;
import com.example.graphql_poc.repository.BookRepository;
import com.example.graphql_poc.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository; // Mock do Repositório

    @InjectMocks
    private BookService bookService; // Injeta os Mocks na classe de Serviço

    @Test
    void testFindAll() {
        // Mocking
        Book book1 = new Book(1L, "Book 1", "Author A", 2000);
        Book book2 = new Book(2L, "Book 2", "Author B", 2010);
        List<Book> expectedBooks = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(expectedBooks);

        // Ação
        List<Book> actualBooks = bookService.findAll();

        // Verificação
        assertEquals(2, actualBooks.size());
        assertEquals("Book 1", actualBooks.get(0).getTitle());
        verify(bookRepository, times(1)).findAll(); // Verifica se o método foi chamado
    }

    @Test
    void testFindById_Found() {
        // Mocking
        Long id = 1L;
        Book expectedBook = new Book(id, "Test Book", "Test Author", 2023);
        when(bookRepository.findById(id)).thenReturn(Optional.of(expectedBook));

        // Ação
        Optional<Book> actualBook = bookService.findById(id);

        // Verificação
        assertTrue(actualBook.isPresent());
        assertEquals("Test Book", actualBook.get().getTitle());
    }

    @Test
    void testSaveBook() {
        // Mocking
        Book bookToSave = new Book(null, "New Book", "New Author", 2024);
        Book savedBook = new Book(3L, "New Book", "New Author", 2024);

        when(bookRepository.save(bookToSave)).thenReturn(savedBook);

        // Ação
        Book result = bookService.saveBook(bookToSave);

        // Verificação
        assertNotNull(result.getId());
        assertEquals(3L, result.getId());
        verify(bookRepository, times(1)).save(bookToSave);
    }
}