package com.example.demo.model.searchengine;

import com.example.demo.model.bookmanager.Book;

import java.util.ArrayList;

public interface SearchStrategy {
    public ArrayList<Book> search(String search, ArrayList<Book> books);
}