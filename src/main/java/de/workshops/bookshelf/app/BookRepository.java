package de.workshops.bookshelf.app;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

@Repository
class BookRepository {
    private final ObjectMapper mapper;
    private final ResourceLoader resourceLoader;
    private List<Book> books;

    BookRepository(final ObjectMapper mapper, final ResourceLoader resourceLoader) {
        this.mapper = mapper;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    void init() throws IOException {
        final var resource = resourceLoader.getResource("classpath:books.json");
        books = mapper.readValue(resource.getInputStream(), new TypeReference<>() {});
    }

    List<Book> findAllBooks() {
        return books;
    }

    Book findByIsbn(final String isbn) throws BookException {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(BookException::new);
    }

    Book findByName(final String name) throws BookException {
        return books.stream()
                .filter(book -> book.getAuthor().startsWith(name))
                .findFirst()
                .orElseThrow(BookException::new);
    }
}
