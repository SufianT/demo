package com.example.demo.web;

import com.example.demo.model.Book;
import com.example.demo.model.LibrarySystem;
import com.example.demo.model.searchengine.SearchInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
public class SearchController {
    private SearchInterface searchInterface;
    private LibrarySystem ls;
    @GetMapping("/Search")
    public ArrayList<Book> search(String searchedString){
        ArrayList<Book> books = ls.getBookList();
        ArrayList<Book> returnedArray = new ArrayList<>();
        returnedArray =searchInterface.searchByAuthorAndTitle(searchedString, books);
        if (searchInterface.searchByISBN(searchedString, books).isEmpty()){
            return returnedArray;
        }
        if (returnedArray.contains(searchInterface.searchByISBN(searchedString, books).get(0))){
            returnedArray.remove(searchInterface.searchByISBN(searchedString, books).get(0));
        }
        returnedArray.addFirst(searchInterface.searchByISBN(searchedString, books).get(0));
        return returnedArray;
    }

}
