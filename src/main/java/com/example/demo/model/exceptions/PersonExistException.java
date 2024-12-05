package com.example.demo.model.exceptions;

import com.example.demo.model.User;

public class PersonExistException extends Exception {
    public PersonExistException() {
        super("User with the specified person number already exists!");
    }
}
