package de.workshops.bookshelf.app;

import de.workshops.bookshelf.lib.Author;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorRestController.class);

    private final Author author;

    public AuthorRestController(final Author author) {
        this.author = author;
        LOGGER.info("***** Author name is {}", this.author.getName());
    }
}
