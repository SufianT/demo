package com.example.demo.model;

import java.util.ArrayList;

import com.example.demo.model.Records.BorrowedBook;

public class User extends Person {
    private ArrayList<BorrowedBook> borrowedBooks;

    public User(String name, String email, String password, String id) {
        super(name, email, password, id);
        borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book, LoanPeriod loanPeriod) { // LoanPeriod for startdate and enddate
            borrowedBooks.add(LibrarySystem.getLibrary().borrow(book, loanPeriod));
    }

    public void returnBook(Book book) { // We have to null LoanPeriod...
        // TODO return book to library
        borrowedBooks.remove(book);

    }

    public ArrayList<BorrowedBook> getBookList() {
        return borrowedBooks;
    }

}
