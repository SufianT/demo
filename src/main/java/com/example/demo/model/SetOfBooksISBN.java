package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetOfBooksISBN implements SetOfBooksISBNInterface{
    public Set<String> setOfBooksISBN(ArrayList<Book> books){
        Set<String> wordSet = new HashSet<>();
        for (Book book:books){
            wordSet.add(book.getISBN());
        }
        return wordSet;
    }

}
