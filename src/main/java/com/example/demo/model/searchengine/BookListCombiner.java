package com.example.demo.model.searchengine;

import com.example.demo.model.Book;
import com.example.demo.model.LibrarySystem;

import java.util.ArrayList;
import java.util.List;

/*
Combines the searchByISBN and searchByAuthorAndTitle
and returns a List of string containing ISBN of wanted books.
(After search)
 */

public class BookListCombiner {
    private LibrarySystem ls;

    public BookListCombiner(LibrarySystem ls) {
        this.ls = ls;
    }

    public List<String> bookListCombiner(String s) {
        StringToWantedWordsInterface w = new StringToWantedWords();
        CalculateMostWantedBookInterface calc = new CalculateMostWantedBook();
        SearchInterface searchIF = new SearchEngine(w, calc);

        List<String> isbn = new ArrayList<>();
        ArrayList<Book> books = ls.getBookList();

        if (s.isBlank()) {
            for (Book book : books) {
                isbn.add(book.getISBN());
            }
            return isbn;
        }

        ArrayList<Book> bookSet = new ArrayList<>();

        for (Book book : searchIF.searchByAuthorAndTitle(s, books)) {
            bookSet.add(book);
        }

        if (searchIF.searchByISBN(s, books).size() == 1) {
            Book isbnBook = searchIF.searchByISBN(s, books).get(0); // Extract the single Book
            if (bookSet.contains(isbnBook)) {
                bookSet.remove(isbnBook);
            }
            bookSet.add(0, isbnBook); // Add at the start of the list
        }

        for (Book book : bookSet) {
            isbn.add(book.getISBN());
        }
        return isbn;
    }
}
