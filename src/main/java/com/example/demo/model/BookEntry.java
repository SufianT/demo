package com.example.demo.model;

public class BookEntry {
    public Book book;
    public int copies;

    public BookEntry() { //DONT REMOVE!
    }

    public BookEntry(Book book, int copies) {
        this.book = book;
        this.copies = copies;
    }

    public Book getBook() {
        return book;
    }
}
