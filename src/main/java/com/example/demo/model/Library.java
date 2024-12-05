package com.example.demo.model;

import java.util.List;

import com.example.demo.model.Records.BorrowedBook;

public class Library {

    List<BookEntry> books;

    public Library(List<BookEntry> books){
        this.books = books;
        for (BookEntry bookEntry : books) {
            System.out.println(bookEntry.book);
        }
    }

    public void addBook(BookEntry book) {
        books.add(book);
    }

    public List<BookEntry> getBookList() {
        return books;
    }

    public BookEntry findInstance(Book book) {
        for (BookEntry bookInstance : books) {
            if (bookInstance.book.equals(book))
                return bookInstance;
        }
        throw new RuntimeException("this should not happen");
    }

    public boolean isAvailable(Book book) {
        return findInstance(book).copies > 0;
    }

    public BorrowedBook borrow(Book book, LoanPeriod period) {
        if (isAvailable(book)) {
            findInstance(book).copies--;
            return new BorrowedBook(book, period);
        }
        throw new RuntimeException("book not avaiblable");
    }

     // public void addBook(Book book) {
    // library.books.add(book);
    // }

    // public void removeBook(Book book) {
    // library.books.remove(book);
    // }
}
