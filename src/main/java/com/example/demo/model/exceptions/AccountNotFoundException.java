package com.example.demo.model.exceptions;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException() {
        super("The account does not exist!");
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}