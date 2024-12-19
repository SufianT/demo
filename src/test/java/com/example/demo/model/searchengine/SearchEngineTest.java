package com.example.demo.model.searchengine;

import com.example.demo.model.Authenticator;
import com.example.demo.model.Book;
import com.example.demo.model.LibrarySystem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SearchEngineTest {


    @Test
    void searchEngingeTest(){
        String input = "the Picture ".toLowerCase();
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        SetOfBookstoString setToString = new SetOfBookstoString();
        StringToWantedWords sTWW = new StringToWantedWords();
        HashMap<String, Integer> hashMap = new HashMap<>();
        CalculateMostWantedBook calc = new CalculateMostWantedBook();


        hashMap = sTWW.searchComplete(input, setToString.setOfBooksToSetString(ls.getBookList()), 0);

        ArrayList<Book> lsarray = new ArrayList();
        lsarray= ls.getBookList();

        SearchEngine se = new SearchEngine(sTWW,calc);
        //assertEquals(1,se.searchByAuthorAndTitle(input,lsarray));
        assertEquals(1,se.searchByISBN("9780451524935",lsarray).size());


    }
}