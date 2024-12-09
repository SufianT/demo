package com.example.demo.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Person {
    private ArrayList<Book> borrowedBooks;

    // No-args constructor for Jackson
    public User() {
        super(null, null, null, null);
    }

    public User(String name, String email, String id, String password) {
        super(name, email, id, password);
        this.borrowedBooks = new ArrayList<Book>();
    }

    public User(String email, String password) {
        super("DefaultName", email, "DefaultID", password);
    }

    public void borrowBook(Book book) {
        // TODO: borrow and add to borrowedbooks
    }

    public void returnBook(Book book) { // We have to null LoanPeriod...
        // TODO return book to library
        borrowedBooks.remove(book);

    }

    public ArrayList<Book> getBookList() {
        return borrowedBooks;
    }

}
