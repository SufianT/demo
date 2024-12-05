package com.example.demo.model;

import java.util.ArrayList;

public class Book {
    private String title;
    private ArrayList<String> author;
    private String isbn;
    private Genre genre;

    public Book() { //DONT REMOVE!

    }

    public Book(String title, ArrayList<String> author, String isbn, Genre genre) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public String toString() {
        return String.format("{title:%s, author:%s, genre:%s}", title, author, genre.toString());
    }

    enum Genre {
        Fantasy,
        Romance,
        Thriller,
        SCIFI,
        Horror,
        Mystery
    }

}
