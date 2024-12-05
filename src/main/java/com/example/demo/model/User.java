package com.example.demo.model;

import java.util.ArrayList;

public class User extends Person {
    private ArrayList<Book> borrowedBooks;

    public User(String name, String email, String id, String password) {
        super(name, email, id, password);
        this.borrowedBooks = new ArrayList<Book>();
    }

    public void borrowBook(Book book) {
        // TODO: borrow and add to borrowedbooks
    }

    public void returnBook(Book book) {
        // TODO: return and remove borrowedbooks
    }
}
