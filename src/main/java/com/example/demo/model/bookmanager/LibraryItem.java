package com.example.demo.model.bookmanager;

public class LibraryItem {
    private Book book;
    private int copies;

    public LibraryItem() { //DONT REMOVE!
    }

    public LibraryItem(Book book, int copies) {
        this.book = book;
        this.copies = copies;
    }

    public Book getBook() {
        return book;
    }

    public int getCopies() {
        return copies;
    }
    
    public void setCopies(int copies) {
        this.copies = copies;
    }
}