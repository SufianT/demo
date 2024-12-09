package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface CalculateMostWantedBookInterface {
    public HashMap<Book, Integer> sortBooksFromSearch(HashMap<String,Integer> hashMap, ArrayList<Book> books);
    public ArrayList<Book> sortHashMapByValue(HashMap<Book, Integer> map);
}
