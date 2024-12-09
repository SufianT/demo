package com.example.demo.model;

import java.util.ArrayList;
import java.util.Map;

public interface SearchInterface {
    public ArrayList<Book> SearchByAuthorAndTitle(String search, ArrayList<Book> books);

    public ArrayList<Book> SearchByISBN(String search,ArrayList<Book> books);
}
