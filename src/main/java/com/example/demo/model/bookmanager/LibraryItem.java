package com.example.demo.model.bookmanager;

/**
 * Represents an item in the library, consisting of a book and the number of copies available.
 * Provides methods to retrieve and update the number of copies of the book.
 * Includes a no-argument constructor required for certain frameworks or serialization.
 */

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