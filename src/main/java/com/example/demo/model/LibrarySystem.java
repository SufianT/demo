package com.example.demo.model;

import java.util.ArrayList;

public class LibrarySystem {
   AuthenticationInterface authenticator;
    Library library;

    public LibrarySystem(Library library){
        this.library=library;
    }

    public void registerAdmin(Admin admin){
        authenticator.registerAdmin(admin);
    }

    public void registerUser(User user){
        authenticator.registerUser(user);
    }

    public void addBook(Book book){
        library.bookList.add(book);
    }
    public void removeBook(Book book){
        library.bookList.remove(book);
    }

//reservation

}
