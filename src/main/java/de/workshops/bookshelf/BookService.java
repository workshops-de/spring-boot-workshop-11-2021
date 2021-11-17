package de.workshops.bookshelf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
class BookService {

    private final BookRepository bookRepository;

    BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }

    Book getByIsbn(final String isbn) throws BookException {
        return bookRepository.findByIsbn(isbn);
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
