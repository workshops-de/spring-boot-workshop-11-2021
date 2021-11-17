package de.workshops.bookshelf.app;

import de.workshops.bookshelf.lib.Author;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AuthorConfiguration {

    @Bean Author author(@Value("${author.name}") String name) {
        final var author = new Author();
        author.setName(name);
        return author;
    }
}
