package com.example.demo.model.searchengine;

import com.example.demo.model.bookmanager.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetOfBooksISBN implements SetOfBooksToSetStringInterface {
    public Set<String> setOfBooksToSetString(ArrayList<Book> books) {
        Set<String> wordSet = new HashSet<>();
        for (Book book : books) {
            wordSet.add(book.getISBN());
        }
        return wordSet;
    }

}
