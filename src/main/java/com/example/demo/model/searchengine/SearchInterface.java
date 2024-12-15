package com.example.demo.model.searchengine;

import com.example.demo.model.Book;

import java.util.ArrayList;

public interface SearchInterface {
    public ArrayList<Book> SearchByAuthorAndTitle(String search, ArrayList<Book> books);

    public ArrayList<Book> SearchByISBN(String search, ArrayList<Book> books);
}
