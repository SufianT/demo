package com.example.demo.model;

import com.example.demo.model.exceptions.PersonExistException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Orchestrates the overall library functionality.
 * Acts as a bridge between the authentication system, database, and library
 * components.
 */

@Component
public class LibrarySystem {
    AuthenticationInterface authenticator;

    public LibrarySystem(AuthenticationInterface authenticator) {
        this.authenticator = authenticator;
    }

    public void registerAdmin(Admin admin) throws PersonExistException {
        authenticator.registerAdmin(admin);
    }

    public void registerUser(User user) throws PersonExistException {
        authenticator.registerUser(user);
    }

    public void addBook(Book book) {
        Database.addBook(book);
    }

    public AuthenticationInterface getAuth() {
        return this.authenticator;
    }

    public ArrayList<Book> getBookList() {
        return Database.getBookList();
    }

    public boolean isAvailable(Book book) {
        for (LibraryItem libraryItem : Database.getLibraryItems()) {
            if (libraryItem.book.equals(book) && libraryItem.getCopies() > 0) {
                return true;
            }
        }
        return false;
    }

}
