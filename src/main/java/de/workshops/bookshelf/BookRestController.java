package de.workshops.bookshelf;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookRestController {

    private final ObjectMapper mapper;
    private final ResourceLoader resourceLoader;
    private List<Book> books;

    public BookRestController(final ObjectMapper mapper, final ResourceLoader resourceLoader) {
        this.mapper = mapper;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() throws IOException {
        final var resource = resourceLoader.getResource("classpath:books.json");
        books = mapper.readValue(resource.getInputStream(), new TypeReference<>() {});
    }

    @GetMapping
    List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/{isbn}")
    Book getByIsbn(@PathVariable String isbn) throws BookException {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(BookException::new);
    }

    @GetMapping(params = "author")
    Book getByAuthor(@RequestParam(value = "author") String name) throws BookException {
        return books.stream()
                .filter(book -> book.getAuthor().startsWith(name))
                .findFirst()
                .orElseThrow(BookException::new);
    }
}
