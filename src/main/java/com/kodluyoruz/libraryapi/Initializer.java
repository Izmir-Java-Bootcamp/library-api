package com.kodluyoruz.libraryapi;

import com.kodluyoruz.libraryapi.model.entity.Author;
import com.kodluyoruz.libraryapi.model.entity.Book;
import com.kodluyoruz.libraryapi.repository.AuthorRepository;
import com.kodluyoruz.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        Author author = authorRepository.save(Author.builder()
                .name("test")
                .surname("author")
                .build());

        for (int i = 0; i < 100; i++) {
            bookRepository.save(Book.builder()
                    .title("test book " + i)
                    .authorId(author.getId())
                    .build());
        }
    }
}
