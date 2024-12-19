package com.example.demo.model;

import java.util.List;

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
        for (int i = 0; i<authors.size();i++){
            bookString+=" "+authors.get(i);
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
