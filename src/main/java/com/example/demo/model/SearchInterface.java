package com.example.demo.model;

import java.util.ArrayList;

public interface SearchInterface {
    public ArrayList<Book> SearchByAuthor(String[] authors);

    public ArrayList<Book> SearchBytitle(String title);

    public ArrayList<Book> SearchByISBN(int ISBN);
}
