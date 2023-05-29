package com.cache;

import com.cache.service.BookService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class ComoCachearUmaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComoCachearUmaApiApplication.class, args);
	}


	@Bean
	public ApplicationRunner runner(BookService bookService) {
		return args -> {
			System.out.println("-------------------------------------");
			System.out.println("Livros chamada 1: " + bookService.getBooks());
			System.out.println("Livros chamada 2: " + bookService.getBooks());
			System.out.println("Livro chamada por id 1: " + bookService.getBookById(1L));
			System.out.println("Livro chamada por id 1: " + bookService.getBookById(1L));
			System.out.println("-------------------------------------");
		};
	}
}


