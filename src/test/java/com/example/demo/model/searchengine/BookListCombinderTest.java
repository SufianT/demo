package com.example.demo.model.searchengine;

import com.example.demo.model.Authenticator;
import com.example.demo.model.LibrarySystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookListCombinderTest {
    @Test
    void testingALL() {
        BookListCombinder test = new BookListCombinder(new LibrarySystem(new Authenticator()));
        var res = test.bookListCombinder("        kill Khaled Lee");
        //assertTrue(res.get(0).toString().equals("To Kill a Mockingbird Harper Lee Sufi"));
        assertEquals(1,test.bookListCombinder("  kill Khaled"));
    }

}