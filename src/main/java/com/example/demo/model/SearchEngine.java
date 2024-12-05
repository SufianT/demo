package com.example.demo.model;

import java.util.ArrayList;

public class SearchEngine implements SearchInterface {
    ArrayList<Book> books = new ArrayList<>();

    @Override
    public ArrayList<Book> SearchByAuthor(String[] authors) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SearchByAuthor'");
    }

    @Override
    public ArrayList<Book> SearchBytitle(String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SearchBytitle'");
    }

    @Override
    public ArrayList<Book> SearchByISBN(int ISBN) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SearchByISBN'");
    }

}
