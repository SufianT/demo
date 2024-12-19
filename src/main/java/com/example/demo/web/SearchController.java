package com.example.demo.web;

import com.example.demo.model.LibrarySystem;
import com.example.demo.model.searchengine.BookListCombinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    private BookListCombinder s;

    public SearchController(LibrarySystem librarySystem) {
        BookListCombinder s = new BookListCombinder(librarySystem);
        this.s = s;
    }

    @GetMapping("/search")
    public List<String> search(@RequestParam String query) {
        return s.bookListCombinder(query);
    }

}
