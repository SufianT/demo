package com.example.demo.model.searchengine;

import com.example.demo.model.Book;
import com.example.demo.model.LibrarySystem;

import java.util.ArrayList;

public class BookListCombinder {
    private LibrarySystem ls;
    //private SearchInterface si;

    public BookListCombinder(LibrarySystem ls) {
        this.ls = ls;
    }

    public ArrayList<Book> bookListCombinder(String s){
        StringToWantedWordsInterface w = new StringToWantedWords();
        CalculateMostWantedBookInterface calc = new CalculateMostWantedBook();
        SearchInterface searchIF = new SearchEngine(w,calc);

        ArrayList<Book> books= new ArrayList<>();
        books=ls.getBookList();
        if(s.isBlank()){
            return books;
        }

        ArrayList<Book> bookSet = new ArrayList<>();
        for (Book book : searchIF.searchByAuthorAndTitle(s,books)){
            bookSet.add(book);
        }
        if (searchIF.searchByAuthorAndTitle(s,books).size()==1) {
            if (bookSet.contains(searchIF.searchByAuthorAndTitle(s,books))) {
                bookSet.remove(searchIF.searchByAuthorAndTitle(s, books).get(0));
            }
            bookSet.addFirst(searchIF.searchByAuthorAndTitle(s,books).get(0));
        }
        return bookSet;
    }

}
