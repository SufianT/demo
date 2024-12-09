package com.example.demo.model;

import java.util.ArrayList;
import java.util.Map;

public class SearchEngine implements SearchInterface {

    private StringToWantedWordsInterface wI;
    private CalculateMostWantedBookInterface cI;
    private SetOfBookstoStringInterface setTitleAuthor;
    private SetOfBooksISBNInterface setISBN;

    public SearchEngine(StringToWantedWordsInterface w, CalculateMostWantedBookInterface c)
    {

    }
    public ArrayList<Book> SearchByAuthorAndTitle(String input, ArrayList<Book> books) {

        return cI.sortHashMapByValue(cI.sortBooksFromSearch(wI.searchComplete(input,setTitleAuthor.setOfBookstoString(books),3),books));

    }

    @Override
    public ArrayList<Book> SearchByISBN(String input,ArrayList<Book> books) {
        ArrayList<Book> bookFromISBNSearch = new ArrayList<>();
        if (wI.searchComplete(input,setISBN.setOfBooksISBN(books),0).isEmpty()){
            return bookFromISBNSearch;
        }
        for (String key : wI.searchComplete(input,setISBN.setOfBooksISBN(books),0).keySet()) {
            String searchedISBN = key;
            for (Book book : books){
                if(book.getISBN()==searchedISBN) {
                    bookFromISBNSearch.add(book);
                    return bookFromISBNSearch;
                }
            }
        }
        return bookFromISBNSearch;
    }

}
