package com.example.demo.web;

import com.example.demo.model.Book;
import com.example.demo.model.LibrarySystem;
import com.example.demo.model.searchengine.SearchInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {
    private SearchInterface searchInterface;
    private LibrarySystem ls;

    @GetMapping("/search")
    public ArrayList<Book> searkch(@RequestParam String searchedString) {
        ArrayList<Book> books = ls.getBookList();
        ArrayList<Book> returnedArray = new ArrayList<>();

        // Search by title and author
        returnedArray = searchInterface.searchByAuthorAndTitle(searchedString, books);

        // Search by ISBN and prioritise it
        List<Book> isbnMatches = searchInterface.searchByISBN(searchedString, books);
        if (!isbnMatches.isEmpty()) {
            Book isbnMatch = isbnMatches.get(0);
            returnedArray.remove(isbnMatch);
            returnedArray.add(0, isbnMatch);
        }
        return returnedArray;
    }

}
