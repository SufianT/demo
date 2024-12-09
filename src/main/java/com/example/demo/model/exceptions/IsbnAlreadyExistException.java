package com.example.demo.model.exceptions;

public class IsbnAlreadyExistException extends Exception{
    public IsbnAlreadyExistException() {
        super("ISBN already exist");
    }

    public IsbnAlreadyExistException(String message) {
        super(message);
    }
}
