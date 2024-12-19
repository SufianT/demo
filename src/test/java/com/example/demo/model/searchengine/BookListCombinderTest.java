package com.example.demo.model.searchengine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookListCombinderTest {
    @Test
    void testingALL() {
        BookListCombinder test = new BookListCombinder();
        assertEquals(1,test.BookListCombinder("        kill Khaled Lee"));
    }

}