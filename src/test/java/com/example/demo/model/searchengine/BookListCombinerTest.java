package com.example.demo.model.searchengine;

import com.example.demo.model.usermanagement.Authenticator;
import com.example.demo.model.bookmanager.Book;
import com.example.demo.model.LibrarySystem;
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

        SearchStrategy s = new SearchByISBN(sTWW, calc);

        // ISBN TESTER
        assertEquals(false, s.search(input, lsArray).isEmpty());
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