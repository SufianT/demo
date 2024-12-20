package com.example.demo.model.searchengine;

import com.example.demo.model.bookmanager.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetOfBookstoString implements SetOfBooksToSetStringInterface {

    public Set<String> setOfBooksToSetString(ArrayList<Book> books) {
        // Create a Set to store unique words
        Set<String> wordSet = new HashSet<>();

        // Loop through each book in the array
        for (Book book : books) {

            String[] words = book.toString().toLowerCase().split("\\s+");
            // Add each word to the Set
            for (String word : words) {
                wordSet.add(word);
            }

        }
        return wordSet;
    }
}
