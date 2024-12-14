package com.example.demo.model.searchengine;

import com.example.demo.model.Book;

import java.util.ArrayList;
import java.util.Set;

public interface SetOfBooksISBNInterface {
    public Set<String> setOfBooksISBN(ArrayList<Book> books);
}
