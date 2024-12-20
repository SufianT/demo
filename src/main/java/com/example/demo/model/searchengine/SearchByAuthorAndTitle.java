package com.example.demo.model.searchengine;

import com.example.demo.model.bookmanager.Book;
import com.example.demo.model.searchengine.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class SearchByAuthorAndTitle implements SearchStrategy {

    private StringToWantedWordsInterface wI;
    private CalculateMostWantedBookInterface cI;
    private SetOfBooksToSetStringInterface setTitleAuthor;

    public SearchByAuthorAndTitle(StringToWantedWordsInterface w, CalculateMostWantedBookInterface c) {
        setTitleAuthor = new SetOfBookstoString();
        this.wI = w;
        this.cI = c;
    }

    public ArrayList<Book> search(String input, ArrayList<Book> books) {
        Set<String> SetAuthorAndTitle = setTitleAuthor.setOfBooksToSetString(books);
        HashMap<String, Integer> hashWords = wI.searchComplete(input, SetAuthorAndTitle, 2);

        return cI.sortHashMapByValue(cI.sortBooksFromSearch(hashWords, books));

    }
}