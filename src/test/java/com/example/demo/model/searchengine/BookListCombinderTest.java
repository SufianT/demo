package com.example.demo.model.searchengine;

import com.example.demo.model.Authenticator;
import com.example.demo.model.Book;
import com.example.demo.model.LibrarySystem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BookListCombinderTest {
    @Test
    void testISBNSearch(){
        String input = " a  9780061120084 ".toLowerCase();
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        SetOfBookstoString setToString = new SetOfBookstoString();
        StringToWantedWords sTWW = new StringToWantedWords();
        HashMap<String, Integer> hashMap = new HashMap<>();
        CalculateMostWantedBook calc = new CalculateMostWantedBook();
        ArrayList<Book> lsArray = new ArrayList<>();
        lsArray = ls.getBookList();
        BookListCombinder blc = new BookListCombinder(ls);


        SearchEngine se = new SearchEngine(sTWW,calc);

        //ISBN TESTER
        assertEquals(false,se.searchByISBN(input,lsArray).isEmpty());
        assertEquals(false,blc.bookListCombinder(input).isEmpty());
        //first in list
        assertTrue(input.contains(blc.bookListCombinder(input).get(0)));

    }
    @Test
    void matchAspecificBook(){
        String input = "To Kill a Mockingbird".toLowerCase();
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        BookListCombinder blc = new BookListCombinder(ls);

        assertEquals("9780061120084",blc.bookListCombinder(input).get(0));
    }

}