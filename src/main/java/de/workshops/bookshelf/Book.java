package de.workshops.bookshelf;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Book {

    private String title;
    private String description;
    private String author;
    private String isbn;
}
