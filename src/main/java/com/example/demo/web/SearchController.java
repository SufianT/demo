package com.example.demo.web;

import com.example.demo.model.Book;
import com.example.demo.model.LibrarySystem;
import com.example.demo.model.searchengine.BookListCombinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/search")
    public Set<String> search(@RequestParam String query){
        return s.bookListCombinder(query);
    }

}
