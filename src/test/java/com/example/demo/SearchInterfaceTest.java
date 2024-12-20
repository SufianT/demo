package com.example.demo;

import com.example.demo.model.LibrarySystem;
import com.example.demo.model.bookmanager.Book;
import com.example.demo.model.searchengine.*;
import com.example.demo.model.usermanagement.Authenticator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchInterfaceTest {

    @Test
    void searchISBN() {
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        StringToWantedWords sTWW = new StringToWantedWords();
        CalculateMostWantedBook calc = new CalculateMostWantedBook();

        ArrayList<Book> lsarray = ls.getBookList();

        SearchStrategy s = new SearchByISBN(sTWW, calc);
        // assertEquals(1,se.searchByAuthorAndTitle(input,lsarray));
        assertEquals(1, s.search("9780451524935", lsarray).size());
    }
    @Test
    void searchTitle(){
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        StringToWantedWords sTWW = new StringToWantedWords();
        CalculateMostWantedBook calc = new CalculateMostWantedBook();

        ArrayList<Book> lsarray = ls.getBookList();
        SearchStrategy s = new SearchByAuthorAndTitle(sTWW, calc);
        assertEquals("To Kill a Mockingbird",s.search("To Kill a Mockingbird", lsarray).getFirst().getTitle());
    }
    @Test
    void searchAuthor(){
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        StringToWantedWords sTWW = new StringToWantedWords();
        CalculateMostWantedBook calc = new CalculateMostWantedBook();

        ArrayList<Book> lsarray = ls.getBookList();
        SearchStrategy s = new SearchByAuthorAndTitle(sTWW, calc);
        assertTrue(s.search("George Orwell", lsarray).getFirst().getAuthors().contains("George Orwell"));
        //Could get an error if a Title contains George Orwell
    }
    @Test
    void searchEngingeTest() {
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        StringToWantedWords sTWW = new StringToWantedWords();
        CalculateMostWantedBook calc = new CalculateMostWantedBook();

        ArrayList<Book> lsarray = ls.getBookList();

        SearchStrategy s = new SearchByISBN(sTWW, calc);
        // assertEquals(1,se.searchByAuthorAndTitle(input,lsarray));
        assertEquals(1, s.search("9780451524935", lsarray).size());
    }
}