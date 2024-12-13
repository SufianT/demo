package com.example.demo.model.exceptions;

public class BookNotAvailableException extends Exception {
    public BookNotAvailableException() {
        super("Book unavailable");
    }

    public BookNotAvailableException(String message) {
        super(message);
    }
}
