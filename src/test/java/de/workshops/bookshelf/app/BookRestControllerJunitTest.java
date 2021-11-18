package de.workshops.bookshelf.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookRestControllerJunitTest {

    @Mock BookService serviceMock;
    @Mock BookshelfProperties properties;

    @InjectMocks
    BookRestController controller;

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
