package de.workshops.bookshelf.app;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookRestControllerTest {

    @Autowired
    BookRestController bookRestController;

    @SpyBean BookService bookServiceMock;

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper mapper;

    @Test
    void testGetAllBooks() {
        when(bookServiceMock.getAllBooks()).thenReturn(getExpectedListOfBooks());

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

    @Test
    void testGetAllBooksWithMockMvc() throws Exception {
        final var mvcResult = mockMvc.perform(get("/book"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andReturn();

        final var resultAsJson = mvcResult.getResponse().getContentAsString();
        List<Book> books = mapper.readValue(resultAsJson, new TypeReference<List<Book>>() {});

        assertThat(books).hasSize(3)
                .extracting("author")
                .containsExactlyInAnyOrder("Erich Gamma", "Robert C. Martin", "Gottfried Wolmeringer");
    }

    private List<Book> getExpectedListOfBooks() {
        final var book1 = Book.builder().author("Erich Gamma").isbn("978-0201633610").title("Design Pattern").build();
        final var book2 = Book.builder().author("Robert C. Martin").isbn("978-3826655487").title("Clean Code").build();
        final var book3 = Book.builder().author("Gottfried Wolmeringer").isbn("978-3836211161").title("Coding for Fun").build();
        return List.of(book1, book2, book3);
    }

}