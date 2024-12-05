package com.example.demo.model;

public class Book {
    private String name;
    private String[] author;
    private boolean isBorrowed;
    private Long ISBN;
    private Genre genre;

    public Book(String name, String[] author, Long ISBN, Genre genre) {
        this.name = name;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.isBorrowed = false;
    }

    public String getName() {
        return name;
    }

    public String[] getAuthor() {
        return author;
    }

    public Long getISBN() {
        return ISBN;
    }

    public Genre getGenre() {
        return genre;
    }

    public boolean getIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
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
