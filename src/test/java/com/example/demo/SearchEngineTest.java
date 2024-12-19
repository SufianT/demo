package com.example.demo;

import com.example.demo.model.LibrarySystem;
import com.example.demo.model.bookmanager.Book;
import com.example.demo.model.searchengine.CalculateMostWantedBook;
import com.example.demo.model.searchengine.SearchEngine;
import com.example.demo.model.searchengine.StringToWantedWords;
import com.example.demo.model.usermanagement.Authenticator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchEngineTest {

    @Test
    void searchEngingeTest() {
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        StringToWantedWords sTWW = new StringToWantedWords();
        CalculateMostWantedBook calc = new CalculateMostWantedBook();

        ArrayList<Book> lsarray = ls.getBookList();

        SearchEngine se = new SearchEngine(sTWW, calc);
        // assertEquals(1,se.searchByAuthorAndTitle(input,lsarray));
        assertEquals(1, se.searchByISBN("9780451524935", lsarray).size());
    }
}