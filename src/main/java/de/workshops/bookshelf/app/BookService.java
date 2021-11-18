package de.workshops.bookshelf.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

@Service
class BookService {

    private final BookRepository bookRepository;
    private final JdbcBookRepository jdbcBookRepository;
    private final JpaBookRepository jpaBookRepository;

    BookService(final BookRepository bookRepository, final JdbcBookRepository jdbcBookRepository,
            final JpaBookRepository jpaBookRepository) {
        this.bookRepository = bookRepository;
        this.jdbcBookRepository = jdbcBookRepository;
        this.jpaBookRepository = jpaBookRepository;
    }

    List<Book> getAllBooks() {
        final var allBooks = jpaBookRepository.findAll();
        return StreamSupport.stream(allBooks.spliterator(), false)
                .collect(Collectors.toList());
    }

    Book getByIsbn(final String isbn) throws BookException {
        return jdbcBookRepository.findByIsbn(isbn);
    }

    Book getByAuthor(final String name) throws BookException {
        return jpaBookRepository.findByAuthorLike("%"+name+"%");
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
