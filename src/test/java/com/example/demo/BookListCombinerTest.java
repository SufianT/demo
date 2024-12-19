package com.example.demo;

import com.example.demo.model.LibrarySystem;
import com.example.demo.model.bookmanager.Book;
import com.example.demo.model.searchengine.BookListCombiner;
import com.example.demo.model.searchengine.CalculateMostWantedBook;
import com.example.demo.model.searchengine.SearchEngine;
import com.example.demo.model.searchengine.StringToWantedWords;
import com.example.demo.model.usermanagement.Authenticator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookListCombinerTest {
    @Test
    void testISBNSearch() {
        String input = " a  9780061120084 ".toLowerCase();
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        StringToWantedWords sTWW = new StringToWantedWords();
        CalculateMostWantedBook calc = new CalculateMostWantedBook();
        ArrayList<Book> lsArray = new ArrayList<>();
        lsArray = ls.getBookList();
        BookListCombiner blc = new BookListCombiner(ls);

        SearchEngine se = new SearchEngine(sTWW, calc);

        // ISBN TESTER
        assertEquals(false, se.searchByISBN(input, lsArray).isEmpty());
        assertEquals(false, blc.bookListCombiner(input).isEmpty());
        // first in list
        assertTrue(input.contains(blc.bookListCombiner(input).get(0)));
    }

    @Test
    void matchAspecificBook() {
        String input = "To Kill a Mockingbird".toLowerCase();
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        BookListCombiner blc = new BookListCombiner(ls);

        assertEquals("9780061120084", blc.bookListCombiner(input).get(0));
    }
}