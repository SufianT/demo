package com.example.demo.web;

import com.example.demo.model.Book;
import com.example.demo.model.SearchInterface;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

public class SearchController {
    private SearchInterface searchInterface;
    @GetMapping("/Search")
    public ArrayList<Book> search(String searchedString,ArrayList<Book> books){
        ArrayList<Book> returnedArray = new ArrayList<>();
        returnedArray =searchInterface.SearchByAuthorAndTitle(searchedString, books);
        if (searchInterface.SearchByISBN(searchedString, books).isEmpty()){
            return returnedArray;
        }
        if (returnedArray.contains(searchInterface.SearchByISBN(searchedString, books).get(0))){
            returnedArray.remove(searchInterface.SearchByISBN(searchedString, books).get(0));
        }
        returnedArray.addFirst(searchInterface.SearchByISBN(searchedString, books).get(0));
        return returnedArray;
    }


}
