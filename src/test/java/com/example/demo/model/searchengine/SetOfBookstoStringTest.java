package com.example.demo.model.searchengine;

import com.example.demo.model.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import com.example.demo.model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetOfBookstoStringTest {
    @Test
    void makeASetFrom2books(){
        ArrayList<String> k = new ArrayList<>();
        k.add("Erik Svansson");
        k.add("Roselina Bar");
        Genre genre = Genre.Fantasy;
        com.example.demo.model.Book test = new com.example.demo.model.Book("Pokemon och Ash","123456789",k,"a",genre);
        com.example.demo.model.Book a = new com.example.demo.model.Book("En Erik som rullade ut ur en bar","1234567890",k,"a",genre);
        ArrayList<Book> b = new ArrayList<>();
        b.add(test);
        b.add(a);
        SetOfBookstoString set = new SetOfBookstoString();
        HashSet<String> l = new HashSet<>();
        l.add("erik"); l.add("som"); l.add("bar"); l.add("pokemon"); l.add("och"); l.add("ash");
        l.add("roselina"); l.add("en"); l.add("ur"); l.add("rullade"); l.add("svansson"); l.add("ut");
        assertEquals(l,set.setOfBooksToSetString(b));

    }

}