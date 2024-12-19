package com.example.demo.web;

import com.example.demo.model.searchengine.SearchEngine;
import com.example.demo.model.searchengine.SearchInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class SearchController {
    private SearchInterface se;

    public SearchController(SearchEngine se) {
        this.se = se;
    }

    @GetMapping("/search")
    public Set<String> search(@RequestParam String input) {
        // Search by everything
        return se.find(input);
    }
}
