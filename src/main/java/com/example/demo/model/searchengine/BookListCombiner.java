package com.example.demo.model.searchengine;

import com.example.demo.model.LibrarySystem;
import com.example.demo.model.bookmanager.Book;

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
        SearchStrategy searchISBN = new SearchByISBN(w, calc);
        SearchStrategy searchAuthorAndTitle = new SearchByAuthorAndTitle(w, calc);

        List<String> isbn = new ArrayList<>();
        ArrayList<Book> books = ls.getBookList();

        if (s.isBlank()) {
            for (Book book : books) {
                isbn.add(book.getISBN());
            }
            return isbn;
        }

        ArrayList<Book> bookSet = new ArrayList<>();

        for (Book book : searchAuthorAndTitle.search(s, books)) {
            bookSet.add(book);
        }

        if (!searchISBN.search(s, books).isEmpty()) {
            for (Book book : searchISBN.search(s, books)){
                if (bookSet.contains(book)) {
                    bookSet.remove(book);
                }
                bookSet.add(0, book);
            }
        }

        for (Book book : bookSet) {
            isbn.add(book.getISBN());
        }
        return isbn;
    }
}
