package com.example.demo.model.searchengine;

import com.example.demo.model.bookmanager.Book;
import com.example.demo.model.searchengine.*;

import java.util.ArrayList;

public class SearchByISBN implements SearchStrategy {

    private StringToWantedWordsInterface wI;
    private SetOfBooksToSetStringInterface setISBN;

    public SearchByISBN(StringToWantedWordsInterface w, CalculateMostWantedBookInterface c) {
        setISBN = new SetOfBooksISBN();
        this.wI = w;
    }

    public ArrayList<Book> search(String input, ArrayList<Book> books) {
        ArrayList<Book> bookFromISBNSearch = new ArrayList<>();
        if (wI.searchComplete(input, setISBN.setOfBooksToSetString(books), 0).isEmpty()) {
            return bookFromISBNSearch;
        }
        for (String key : wI.searchComplete(input, setISBN.setOfBooksToSetString(books), 0).keySet()) {
            String searchedISBN = key;
            for (Book book : books) {
                if (book.getISBN() == searchedISBN) {
                    bookFromISBNSearch.add(book);
                    return bookFromISBNSearch;
                }
            }
        }
        return bookFromISBNSearch;
    }
}