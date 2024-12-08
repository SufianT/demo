package com.example.demo.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class Library {
    Book book;
    ArrayList<Book> books= new ArrayList<>();
   
    public void addBook(Book book) {
        books.add(book);
    }
//hashmap
    public ArrayList<Book> getBookList() {
        return books;
    }
}

