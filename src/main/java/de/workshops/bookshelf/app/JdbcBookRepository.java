package de.workshops.bookshelf.app;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcBookRepository {

    private final JdbcTemplate template;

    public JdbcBookRepository(final JdbcTemplate template) {
        this.template = template;
    }

    List<Book> findAllBooks() {
        String query = "SELECT * FROM BOOK";
        return template.query(query, new BeanPropertyRowMapper<>(Book.class));
    }

    Book findByIsbn(String isbn) throws BookException {
        String query = "SELECT * FROM BOOK WHERE ISBN=?";
        try {
            return template.queryForObject(query, new BeanPropertyRowMapper<>(Book.class), isbn);
        } catch (EmptyResultDataAccessException e) {
            throw new BookException("No such book in shelf");
        }
    }
}
