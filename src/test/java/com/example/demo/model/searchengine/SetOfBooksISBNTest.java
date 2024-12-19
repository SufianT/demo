package com.example.demo.model.searchengine;

import com.example.demo.model.Authenticator;
import com.example.demo.model.LibrarySystem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetOfBooksISBNTest {
    @Test
    void ISBNtest(){
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        SetOfBooksISBN setISBN = new SetOfBooksISBN();
        ArrayList array = new ArrayList();
        for (int i =0; i<ls.getBookList().size();i++){
            array.add(ls.getBookList().get(i));
        }
        //Vet att det inte ger ngt men erroren visa att man får rätt och att List=Array i detta fallet.
        assertEquals(setISBN.setOfBooksToSetString(ls.getBookList()),setISBN.setOfBooksToSetString(array));

    }



}