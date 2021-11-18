package de.workshops.bookshelf.app;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookRestControllerTest {

    @Autowired
    BookRestController bookRestController;

    @Test
    void testGetAllBooks() {
        final var allBooks = bookRestController.getAllBooks();

        assertEquals(3, allBooks.size());

        assertThat(allBooks).hasSize(3);
    }

    @Test
    void testGetByIsbn() {
        final var response = bookRestController.getByIsbn("978-3826655487");

        assertThat(response.getBody())
                .hasFieldOrPropertyWithValue("author", "Robert C. Martin");
    }
}