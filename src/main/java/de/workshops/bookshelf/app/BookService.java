package de.workshops.bookshelf.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
class BookService {

    private final BookRepository bookRepository;
    private final JdbcBookRepository jdbcBookRepository;

    BookService(final BookRepository bookRepository, final JdbcBookRepository jdbcBookRepository) {
        this.bookRepository = bookRepository;
        this.jdbcBookRepository = jdbcBookRepository;
    }

    List<Book> getAllBooks() {
        return jdbcBookRepository.findAllBooks();
    }

    Book getByIsbn(final String isbn) throws BookException {
        return jdbcBookRepository.findByIsbn(isbn);
    }

    Book getByAuthor(final String name) throws BookException {
        return bookRepository.findByName(name);
    }

    List<Book> searchBooks(final BookSearchRequest searchRequest) throws BookException {
        final var foundBooks = new ArrayList<Book>();
        if (searchRequest.getAuthor() != null) {
            final var byAuthor = bookRepository.findByName(searchRequest.getAuthor());
            foundBooks.add(byAuthor);
        }
        if (searchRequest.getIsbn() != null) {
            final var byIsbn = bookRepository.findByIsbn(searchRequest.getIsbn());
            foundBooks.add(byIsbn);
        }
        return foundBooks;
    }
}
