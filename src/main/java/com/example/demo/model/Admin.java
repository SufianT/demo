package com.example.demo.model;

public class Admin extends Person {

    LibrarySystem library;

    public Admin(String name, String email, String id, String password, LibrarySystem library) {
        super(name, email, id, password);
        this.library = library;
    }

    public void addBook(Book book) {
        // TODO: add a book to the library
    }

    public void removeBook() {
        // TODO: remove a book from the library
    }
}