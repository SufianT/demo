package com.example.demo.model;

import java.util.ArrayList;

public class Library {
    private SearchInterface search;
    ArrayList<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }
}
