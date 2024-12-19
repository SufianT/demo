package com.example.demo.model.searchengine;

import com.example.demo.model.bookmanager.Book;

import java.util.ArrayList;
import java.util.HashMap;

public interface CalculateMostWantedBookInterface {
    public HashMap<Book, Integer> sortBooksFromSearch(HashMap<String, Integer> hashMap, ArrayList<Book> books);

    public ArrayList<Book> sortHashMapByValue(HashMap<Book, Integer> map);
}