package com.example.demo.web;

import com.example.demo.model.Book;
import com.example.demo.model.LibrarySystem;
import com.example.demo.model.searchengine.BookListCombinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Set;

@RestController
public class SearchController {
    private BookListCombinder s;
    public SearchController(LibrarySystem librarySystem){
        BookListCombinder s = new BookListCombinder(librarySystem);
        this.s=s;
    }

    @GetMapping("/Search")
    public Set<String> search(String input){
        return s.bookListCombinder(input);
    }

}
