package com.example.demo.model.searchengine;

import com.example.demo.model.bookmanager.Book;

import java.util.ArrayList;
import java.util.Set;

public interface SetOfBooksToSetStringInterface {
    public Set<String> setOfBooksToSetString(ArrayList<Book> books);
}