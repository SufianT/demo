package com.example.demo.model.searchengine;

import com.example.demo.model.Book;
import com.example.demo.model.LibrarySystem;

import java.util.ArrayList;
import java.util.List;

public class BookListCombinder {
    private LibrarySystem ls;
    // private SearchInterface si;

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
        if (searchIF.searchByAuthorAndTitle(s, books).size() == 1) {
            if (bookSet.containsAll(searchIF.searchByAuthorAndTitle(s, books))) {
                bookSet.remove(searchIF.searchByAuthorAndTitle(s, books).get(0));
            }
            bookSet.addFirst(searchIF.searchByAuthorAndTitle(s, books).get(0));
        }
        for (Book book : bookSet) {
            isbn.add(book.getISBN());
        }
        return isbn;
    }

}
