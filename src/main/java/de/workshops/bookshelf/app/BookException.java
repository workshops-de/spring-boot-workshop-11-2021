package de.workshops.bookshelf.app;

public class BookException extends Exception{

    public BookException() {
        super();
    }

    public BookException(final String message) {
        super(message);
    }
}
