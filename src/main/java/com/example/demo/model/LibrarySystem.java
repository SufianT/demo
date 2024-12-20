package com.example.demo.model;

import com.example.demo.model.bookmanager.Book;
import com.example.demo.model.bookmanager.LibraryItem;
import com.example.demo.model.exceptions.BookNotAvailableException;
import com.example.demo.model.exceptions.PersonExistException;
import com.example.demo.model.usermanagement.Admin;
import com.example.demo.model.usermanagement.AuthenticationInterface;
import com.example.demo.model.usermanagement.Authenticator;
import com.example.demo.model.usermanagement.User;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Orchestrates the overall library functionality.
 * Acts as a bridge between the authentication system, database, and library
 * components.
 */

@Component
public class LibrarySystem {
    private AuthenticationInterface authenticator;

    public LibrarySystem(Authenticator authenticator) {
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

    public Book getBookByISBN(String isbn) throws BookNotAvailableException {
        Book temp = new Book();
        for (Book b : getBookList()) {
            if (b.getISBN().equals(isbn)) {
                temp = b;
            } else
                throw new BookNotAvailableException("ISBN not found in system");
        }
        return temp;
    }

    public boolean isAvailable(Book book) {
        for (LibraryItem libraryItem : Database.getLibraryItems()) {
            if (libraryItem.getBook().equals(book) && libraryItem.getCopies() > 0) {
                return true;
            }
        }
        return false;
    }

}
