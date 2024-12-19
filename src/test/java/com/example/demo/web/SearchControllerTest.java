package com.example.demo.web;

import com.example.demo.model.searchengine.SearchCombined;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchControllerTest {
    @Test
    void searchISBNreturnOneBook(){

        SearchController a = new SearchController();
        assertEquals(0,a.search("Lee").size());
    }

}