package com.cache;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableCaching
public class ComoCachearUmaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComoCachearUmaApiApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(final BookService bookService) {
		return args -> {
			System.out.println("-------------------------------------");
//			System.out.println("Livros chamada 1: " + bookService.getBooks());
//			System.out.println("Livros chamada 2: " + bookService.getBooks());
			System.out.println("Livro chamada por id 1: " + bookService.getBookById(1L));
			System.out.println("Livro chamada por id 1: " + bookService.getBookById(1L));
			System.out.println("-------------------------------------");
		};
	}
}

record Book(Long id, String title) {}

@Service
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
