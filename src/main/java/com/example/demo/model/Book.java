package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
public class Book {

    String title;
    String isbn;
    @JsonProperty("authors")
    ArrayList<String> authors;
    Boolean borrowed;
    String image;
    private Genre genre;
    String borrowedBy;
    @JsonIgnore // Ignore the 'author' field if it still exists in old logic
    private ArrayList<String> author;

    public Book() {
    }
    public Book(String title, String isbn, ArrayList<String>authors, Boolean borrowed, String image, Genre genre, String borrowedBy) {
        this.title=title;
        this.isbn=isbn;
        this.authors=authors;
        this.borrowed=borrowed;
        this.image=image;
        this.genre=genre;
        this.borrowedBy=borrowedBy;
    }

    public boolean getBorrowed(){
        return borrowed;
    }
    public String getTitle(){
        return title;
    }
    public String getISBN(){
        return isbn;
    }
    public ArrayList<String> getAuthor(){
        return authors;
    }
    public Genre getGenre(){return genre;}
    public void setBorrowed(boolean value){
        borrowed=value;
    }
    public String getImage(){
        return image;
    }
    public  String getBorrowedBy(){return borrowedBy;}


}
