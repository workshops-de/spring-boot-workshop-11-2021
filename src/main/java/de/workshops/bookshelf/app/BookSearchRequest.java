package de.workshops.bookshelf.app;

import lombok.Data;

@Data
public class BookSearchRequest {

    private String isbn;
    private String author;
}
