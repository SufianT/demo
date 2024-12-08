package com.example.demo.model;
import com.example.demo.model.exceptions.PersonExistException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LibrarySystem {
   AuthenticationInterface authenticator;
    public Library library;

    public LibrarySystem(Library library){
        this.library=library;
    }

    public void registerAdmin(Admin admin) throws PersonExistException {
        authenticator.registerAdmin(admin);
    }

    public void registerUser(User user) throws PersonExistException {
        authenticator.registerUser(user);
    }

    public void addBook(Book book){
        library.books.add(book);
    }

    public AuthenticationInterface getAuth() {
        return this.authenticator;
    }
    public ArrayList<Book> getBookList(){
        return library.getBookList();
    }


}
