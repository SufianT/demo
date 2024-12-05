package com.example.demo.model;

import org.springframework.stereotype.Service;

@Service
public class LibrarySystem {
    private AuthenticationInterface authenticator;
    private Library library = new Library();

    public LibrarySystem(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    public void addBook(Book book) {
        library.books.add(book);
    }

    public void removeBook(Book book) {
        library.books.remove(book);
    }

    public AuthenticationInterface getAuth() {
        return authenticator;
    }
}