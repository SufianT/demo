package com.example.demo.model.searchengine;

import com.example.demo.model.Book;
import com.example.demo.model.LibrarySystem;

import java.util.ArrayList;
import java.util.List;

/*
Combinds the searchByISBN and searchByAuthorAndTitle
and returns a List of string containing ISBN of wanted books.
(After search)
 */

public class BookListCombinder {
    private LibrarySystem ls;

    public BookListCombinder(LibrarySystem ls) {
        this.ls = ls;
    }

    public List<String> bookListCombinder(String s) {
        StringToWantedWordsInterface w = new StringToWantedWords();
        CalculateMostWantedBookInterface calc = new CalculateMostWantedBook();
        SearchInterface searchIF = new SearchEngine(w, calc);

        List<String> isbn = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();
        books = ls.getBookList();
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
            if (bookSet.contains(searchIF.searchByISBN(s, books))) {
                bookSet.remove(searchIF.searchByISBN(s, books).get(0));
            }
            bookSet.addFirst(searchIF.searchByISBN(s, books).get(0));
        }
        for (Book book : bookSet) {
            isbn.add(book.getISBN());
        }
        return isbn;
    }

}
