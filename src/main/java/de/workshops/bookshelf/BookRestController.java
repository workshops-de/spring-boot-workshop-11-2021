package de.workshops.bookshelf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookRestController {

    private final BookService bookService;

    @Autowired
    public BookRestController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{isbn}")
    public Book getByIsbn(@PathVariable String isbn) throws BookException {
        return bookService.getByIsbn(isbn);
    }

    @GetMapping(params = "author")
    public Book getByAuthor(@RequestParam(value = "author") String name) throws BookException {
        return bookService.getByAuthor(name);
    }

    @PostMapping("/search")
    public List<Book> searchBooks (@RequestBody BookSearchRequest searchRequest) throws BookException {
        return bookService.searchBooks(searchRequest);
    }
}
