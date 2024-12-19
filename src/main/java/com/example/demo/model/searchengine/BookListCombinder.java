package com.example.demo.model.searchengine;

import com.example.demo.model.Book;
import com.example.demo.model.LibrarySystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookListCombinder {
    private LibrarySystem ls;
    //private SearchInterface si;

    public BookListCombinder(LibrarySystem ls) {
        this.ls = ls;
    }

    public Set<String> bookListCombinder(String s){
        StringToWantedWordsInterface w = new StringToWantedWords();
        CalculateMostWantedBookInterface calc = new CalculateMostWantedBook();
        SearchInterface searchIF = new SearchEngine(w,calc);

        Set<String> isbn = new HashSet<>();
        ArrayList<Book> books= new ArrayList<>();
        books=ls.getBookList();
        if(s.isBlank()){
            for(Book book:books){
                isbn.add(book.getISBN());
            }
            return isbn;

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
        for(Book book:bookSet){
            isbn.add(book.getISBN());
        }
        return isbn;
    }

}
