package com.cache.service;

import com.cache.dto.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public
class BookService {
    private final List<Book> books = new ArrayList<>() {{
        add(new Book(1L, "Harry - Livro 1"));
        add(new Book(2L, "Harry - Livro 2"));
        add(new Book(3L, "Harry - Livro 3"));
    }};

    @Cacheable("books")
    public List<Book> getBooks() throws InterruptedException {
        System.out.println("Pesquisando todos os livros...");
        simulateSlowService();
        return books;
    }

    @Cacheable("books")
    public Optional<Book> getBookById(final Long id) throws InterruptedException {
        System.out.println("Pesquisando livro por id...");
        simulateSlowService();
        return books.stream().filter(book -> book.id().equals(id)).findFirst();
    }

    public void simulateSlowService() throws InterruptedException {
        Thread.sleep(5000);
    }
}
