package com.example.demo.model;

import java.util.ArrayList;

import com.example.demo.model.LoanSystem.BorrowedBook;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Person {
    @JsonIgnore
    private ArrayList<Book> borrowHistory;

    // No-args constructor for Jackson
    public User() {
        super(null, null, null, null);
        this.borrowHistory = new ArrayList<Book>();
    }

    public User(String name, String email, String id, String password) {
        super(name, email, id, password);
        this.borrowHistory = new ArrayList<Book>();
    }

    public User(String email, String password) {
        super("DefaultName", email, "DefaultID", password);
    }

    public ArrayList<Book> getBookList() {
        return borrowHistory;
    }

}
