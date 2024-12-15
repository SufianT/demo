package com.example.demo.model.exceptions;

public class PersonExistException extends Exception {
    public PersonExistException() {
        super("User with the specified person number already exists!");
    }
}
