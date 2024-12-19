package com.example.demo.model.searchengine;

import com.example.demo.model.Authenticator;
import com.example.demo.model.Book;
import com.example.demo.model.LibrarySystem;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CalculateMostWantedBookTest {
    @Test
    void calculation(){
        String input = "sufi";
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        SetOfBookstoString setToString = new SetOfBookstoString();
        StringToWantedWords sTWW = new StringToWantedWords();
        HashMap<String,Integer> hashMap = new HashMap<>();
        CalculateMostWantedBook calc = new CalculateMostWantedBook();

        hashMap= sTWW.searchComplete(input,setToString.setOfBooksToSetString(ls.getBookList()),0);
        assertEquals(false,hashMap.isEmpty());
        //assertEquals(false,calc.sortBooksFromSearch(hashMap,ls.getBookList()).isEmpty());
        //assertEquals(false,calc.sortHashMapByValue(calc.sortBooksFromSearch(hashMap,ls.getBookList())).isEmpty());



            // HashMap to store the resulting scores for each book
            HashMap<Book, Integer> bookScores = new HashMap<>();

            // Iterate through the list of books
            for (com.example.demo.model.Book book : ls.getBookList()) {
                int sum = 0; // Initialize the sum for the current book

                // Convert the Book object to String using Book.toString()
                String bookContent = book.toString().toLowerCase();

                // Check each word in the HashMap
                for (String word : hashMap.keySet()) {
                    if (bookContent.contains(word)) {
                        // Add the score associated with the word
                        sum += hashMap.get(word);
                    }
                }

                // Add the book and its calculated score to the result HashMap
                if(sum!=0) {
                    bookScores.put(book, sum);
                }
            }
            assertEquals(false,bookScores.isEmpty());
            // Return the resulting HashMap

        assertEquals(false,calc.sortHashMapByValue(bookScores));

    }

    @Test
    void calculationa(){
        String input = " sufi ";
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        SetOfBookstoString setToString = new SetOfBookstoString();
        StringToWantedWords sTWW = new StringToWantedWords();
        HashMap<String,Integer> hashMap = new HashMap<>();
        CalculateMostWantedBook calc = new CalculateMostWantedBook();

        hashMap= sTWW.searchComplete(input,setToString.setOfBooksToSetString(ls.getBookList()),0);
        assertEquals(false,hashMap.isEmpty());
        //System.out.println(hashMap.entrySet());
        //assertEquals(false,calc.sortBooksFromSearch(hashMap,ls.getBookList()).isEmpty());
        assertEquals(false,calc.sortHashMapByValue(calc.sortBooksFromSearch(hashMap,ls.getBookList())).isEmpty());



        // HashMap to store the resulting scores for each book
        HashMap<Book, Integer> bookScores = new HashMap<>();

        // Iterate through the list of books
        for (com.example.demo.model.Book book : ls.getBookList()) {
            int sum = 0; // Initialize the sum for the current book

            // Convert the Book object to String using Book.toString()
            String bookContent = " "+book.toString().toLowerCase()+" ";

            // Check each word in the HashMap
            for (String word : hashMap.keySet()) {
                if (bookContent.contains(" "+word+" ")) {
                    // Add the score associated with the word
                    sum += hashMap.get(word);
                }
            }

            // Add the book and its calculated score to the result HashMap
            if(sum!=0) {
                bookScores.put(book, sum);
            }
        }
        assertEquals(false,bookScores.isEmpty());
        // Return the resulting HashMap

        assertEquals(false,calc.sortHashMapByValue(bookScores).isEmpty());

    }
    @Test
    void calculationsufi() {
        String input = " Picture ".toLowerCase();
        Authenticator aut = new Authenticator();
        LibrarySystem ls = new LibrarySystem(aut);
        SetOfBookstoString setToString = new SetOfBookstoString();
        StringToWantedWords sTWW = new StringToWantedWords();
        HashMap<String, Integer> hashMap = new HashMap<>();
        CalculateMostWantedBook calc = new CalculateMostWantedBook();

        hashMap = sTWW.searchComplete(input, setToString.setOfBooksToSetString(ls.getBookList()), 2);
        assertEquals(false, hashMap.isEmpty());
        //System.out.println(hashMap.entrySet().size());
        String[] words = input.split("\\s+");
        for (String wordSearched : words) {
            //System.out.println(wordSearched);
        }
        //System.out.println(setToString.setOfBooksToSetString(ls.getBookList()));
        //assertEquals(false,calc.sortBooksFromSearch(hashMap,ls.getBookList()).isEmpty());
        assertEquals(1, calc.sortHashMapByValue(calc.sortBooksFromSearch(hashMap, ls.getBookList())).size());
    }
}