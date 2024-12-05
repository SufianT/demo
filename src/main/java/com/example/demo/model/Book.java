package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
public class Book {

    String title;
    String ISBN;
    ArrayList<String> authors = new ArrayList<>();
    Boolean isBorrowed;
    String ImageURL;
    private Genre genre;
    String borrowedBy;
    public Book() {
    }

    @JsonCreator
    public Book(@JsonProperty("title") String title,
                @JsonProperty("authors") ArrayList<String> authors,
                @JsonProperty("isbn") String isbn,
                @JsonProperty("genre") Genre genre,
                @JsonProperty("borrowed") Boolean borrowed,
                @JsonProperty("image") String image,
                @JsonProperty("borrowedBy") String borrowedBy
                )
    {
        this.title = title;
        this.authors = authors;
        this.ISBN = isbn;
        this.isBorrowed=borrowed;
        this.ImageURL=image;
        this.genre=genre;
        this.borrowedBy=borrowedBy;
    }
    public boolean getIsBorrowed(){
        return isBorrowed;
    }
    public String getTitle(){
        return title;
    }
    public String getISBN(){
        return ISBN;
    }
    public ArrayList<String> getAuthor(){
        return authors;
    }
    public Genre getGenre(){return genre;}
    public void setBorrowed(){
        isBorrowed=true;
    }
    public String getImageURL(){
        return ImageURL;
    }
    public  String getBorrowedBy(){return borrowedBy;}


}
