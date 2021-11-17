package de.workshops.bookshelf.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Book> getByIsbn(@PathVariable String isbn) {
        try {
            final var book = bookService.getByIsbn(isbn);
            return ResponseEntity.ok(book);
        } catch (BookException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(params = "author")
    public Book getByAuthor(@RequestParam(value = "author") String name) throws BookException {
        return bookService.getByAuthor(name);
    }

    @PostMapping(value = "/search")
    public List<Book> searchBooks (@RequestBody BookSearchRequest searchRequest) throws BookException {
        return bookService.searchBooks(searchRequest);
    }

// Either this or @ControllerAdvice in class GlobalExceptionHandler
//    @ExceptionHandler(BookException.class)
//    public ResponseEntity<String> handleBookException(BookException e) {
//        return new ResponseEntity("Cannot find this book", HttpStatus.NOT_FOUND);
//    }
}
