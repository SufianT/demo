package com.example.demo.model;

import java.util.ArrayList;

public class Library {
    Book book;
    ArrayList<Book> bookList= new ArrayList<>();
    public void addBook(Book book) {
        bookList.add(book);
    }
//hashmap
    public ArrayList<Book> getBookList() {
        return bookList;
    }
}

