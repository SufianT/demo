package com.example.demo.model;

import java.util.ArrayList;

public class Library {
    Book book;
    ArrayList<Book> books= new ArrayList<>();
   
    public void addBook(Book book) {
        books.add(book);
    }

    public ArrayList<Book> getBookList() {
        return books;
    }
}

