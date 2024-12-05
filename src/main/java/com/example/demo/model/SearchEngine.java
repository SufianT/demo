package com.example.demo.model;

import java.util.ArrayList;

public class SearchEngine implements SearchInterface{
    Library library;
    ArrayList<Book> bookList= library.bookList;

    @Override
    public ArrayList<Book> SearchByAuthor(String author) {
        return null;
    }

    @Override
    public ArrayList<Book> SearchBytitle(String title) {
        return null;
    }

    @Override
    public ArrayList<Book> SearchByISBN(int ISBN) {
        return null;
    }
}
