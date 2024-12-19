package com.example.demo.web;

import com.example.demo.model.Book;
import com.example.demo.model.searchengine.SearchCombined;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
public class SearchController {
    private SearchCombined s;
    public SearchController(){
        SearchCombined s = new SearchCombined();
        this.s=s;
    }
    @GetMapping("/Search")
    public ArrayList<Book> search(String input){
        return s.combinedSearch(input);
    }

}
