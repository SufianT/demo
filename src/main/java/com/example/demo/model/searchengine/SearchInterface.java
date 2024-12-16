package com.example.demo.model.searchengine;

import com.example.demo.model.Book;

import java.util.ArrayList;

public interface SearchInterface {
    public ArrayList<Book> searchByAuthorAndTitle(String search, ArrayList<Book> books);

    public ArrayList<Book> searchByISBN(String search, ArrayList<Book> books);
}
