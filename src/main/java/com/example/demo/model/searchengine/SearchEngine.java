package com.example.demo.model.searchengine;

import com.example.demo.model.Book;

import java.util.ArrayList;

public class SearchEngine implements SearchInterface {

    private StringToWantedWordsInterface wI;
    private CalculateMostWantedBookInterface cI;
    private SetOfBooksToSetStringInterface setTitleAuthor;
    private SetOfBooksToSetStringInterface setISBN;

    public SearchEngine(StringToWantedWordsInterface w, CalculateMostWantedBookInterface c) {
        setISBN = new SetOfBooksISBN();
        setTitleAuthor = new SetOfBookstoString();
        this.wI = w;
        this.cI = c;
    }

    public ArrayList<Book> searchByAuthorAndTitle(String input, ArrayList<Book> books) {

        return cI.sortHashMapByValue(cI
                .sortBooksFromSearch(wI.searchComplete(input, setTitleAuthor.setOfBooksToSetString(books), 2), books));

    }

    public ArrayList<Book> searchByISBN(String input, ArrayList<Book> books) {
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
