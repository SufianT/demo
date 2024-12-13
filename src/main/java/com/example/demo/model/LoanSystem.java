package com.example.demo.model;

import java.util.HashMap;

public class LoanSystem {
    final LibrarySystem ls;

    HashMap<String, BorrowedBook> borrowList;

    public LoanSystem(LibrarySystem ls) {
        this.ls = ls;
        borrowList = new HashMap<>();
    }

    public void borrow(LibraryItem item, LoanPeriod period, User user) {
        if (ls.isAvailable(item.getBook())) {
            item.setCopies(item.getCopies()-1);
            borrowList.put(user.getId(), new BorrowedBook (item.getBook(), period));
        }
        throw new RuntimeException("book not available");
    }

    public void addBookToList(Book book, User user) {
        user.getBookList().add(book);

    }

    public record BorrowedBook(Book book, LoanPeriod period) {

    }

}
