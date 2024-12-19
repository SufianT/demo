package com.example.demo.model.searchengine;

import com.example.demo.model.Authenticator;
import com.example.demo.model.Book;
import com.example.demo.model.Genre;
import com.example.demo.model.LibrarySystem;

import java.util.ArrayList;
import java.util.List;

public class SearchCombined {
    private LibrarySystem ls;
    private SearchInterface searchInterface;
    public SearchCombined(){
        Authenticator aut = new Authenticator();
        LibrarySystem librarySystem = new LibrarySystem(aut);
        SearchInterface searchInterface = new SearchInterface() {
            @Override
            public ArrayList<Book> searchByAuthorAndTitle(String search, ArrayList<Book> books) {
                return null;
            }

            @Override
            public ArrayList<Book> searchByISBN(String search, ArrayList<Book> books) {
                return null;
            }
        };
        this.searchInterface=searchInterface;
        this.ls=librarySystem;
    }
    public ArrayList<Book> getBookInput(){
        return ls.getBookList();
    }

    public ArrayList<Book> combinedSearch(String searchedString) {
        ArrayList<Book> books = ls.getBookList();

        //ERROR CATCHERS:
        if(searchedString.isBlank()){
            return ls.getBookList();
        }


        /*
        //Hoppas att detta funkar
        if (searchInterface.searchByISBN(searchedString, books)==null) {
            return searchInterface.searchByAuthorAndTitle(searchedString, books);
        }
        if (searchInterface.searchByAuthorAndTitle(searchedString, books).contains(searchInterface.searchByISBN(searchedString, books).get(0))) {
            searchInterface.searchByAuthorAndTitle(searchedString, books).remove(searchInterface.searchByISBN(searchedString, books).get(0));
        }
        searchInterface.searchByAuthorAndTitle(searchedString, books).addFirst(searchInterface.searchByISBN(searchedString, books).get(0));
        return searchInterface.searchByAuthorAndTitle(searchedString, books);
    }*/



        //ALTERNATIVT:

        if ((searchInterface.searchByAuthorAndTitle(searchedString, books) == null) && (searchInterface.searchByISBN(searchedString, books)==null)){
            ArrayList<Book> emptyArray = new ArrayList<Book>();
            return emptyArray;
        }

        ArrayList<Book> AuthorAndTitleList = new ArrayList<>();
        ArrayList<Book> ISBNList = new ArrayList<>();
        AuthorAndTitleList = searchInterface.searchByAuthorAndTitle(searchedString, books);
        ISBNList = searchInterface.searchByISBN(searchedString, books);
        if (ISBNList==null) {
            return AuthorAndTitleList;
        }
        if (AuthorAndTitleList.contains(ISBNList.get(0))) {
            AuthorAndTitleList.remove(ISBNList.get(0));
        }
        AuthorAndTitleList.addFirst(ISBNList.get(0));
        return AuthorAndTitleList;
    }


}