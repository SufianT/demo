package com.example.demo.model.bookmanager;

import java.util.List;

/**
 * Represents a book with details such as title, authors, ISBN, genre, and an optional image.
 * Provides constructors to create a book object and methods to access its properties.
 * Overrides `toString` to generate a string representation of the book and `equals` to compare books based on ISBN.
 */

public class Book {
    private String title;
    private List<String> authors;
    private String isbn;
    private Genre genre;
    private String image;

    public Book() {}

    public Book(String title, String isbn, List<String> authors, String image, Genre genre) {
        this.title = title;
        this.isbn = isbn;
        this.authors = authors;
        this.image = image;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return isbn;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public Genre getGenre() {
        return genre;
    }


    public String getImage() {
        return image;
    }

    public String toString(){
        String bookString = title;
        for (int i = 0; i<=authors.size()-1;i++){
            title+=" "+authors.get(i);
        }
        return bookString;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Book b) {
            return this.isbn.equals(b.getISBN());
        }

        return false;
    }
}
