package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void bookToString(){
        ArrayList<String> autherList = new ArrayList<>();
        autherList.add("Anders Andersson");
        autherList.add("Mårten Mårtensson");
        Genre genre = Genre.Fantasy;

        Book test = new Book("Pokemon och Ash","123456789",autherList,"a",genre);
        assertEquals("Pokemon och Ash Anders Andersson Mårten Mårtensson",test.toString());
    }

}