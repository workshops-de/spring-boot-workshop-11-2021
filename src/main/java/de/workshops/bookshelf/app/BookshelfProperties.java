package de.workshops.bookshelf.app;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "bookshelf")
@Data
public class BookshelfProperties {
    private String name;
    private int since;

    private List<Integer> something;
}
