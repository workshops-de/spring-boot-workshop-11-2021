package de.workshops.bookshelf.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookRestControllerJunitTest {

//    @InjectMocks
//    BookRestController controller;

//    @Mock BookService serviceMock;
//    @Mock BookshelfProperties properties;
    final BookService serviceMock = Mockito.mock(BookService.class);
    final BookshelfProperties properties = Mockito.mock(BookshelfProperties.class);
    BookRestController controller = new BookRestController(serviceMock, properties);

    @BeforeEach
    void init() {
        final var serviceMock = Mockito.mock(BookService.class);
        final var properties = Mockito.mock(BookshelfProperties.class);
        controller = new BookRestController(serviceMock, properties);
    }

    @Test
    void shouldCallService () throws BookException {
        controller.getByIsbn("isbn");
        verify(serviceMock).getByIsbn("isbn");
    }

    @Test
    void callsService() throws BookException {
        when(serviceMock.getByIsbn(anyString())).thenReturn(new Book());

        final var book = controller.getByIsbn("isbn");
        assertThat(book.getBody()).isNotNull();
    }
}
