package de.workshops.bookshelf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookshelfApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookshelfApplication.class);

    public static void main(String[] args) {
        LOGGER.info("******** Starting MyBookshelf *********");
        LOGGER.debug("******** Und hier noch viel mehr Info *********");
        SpringApplication.run(BookshelfApplication.class, args);
    }

}
