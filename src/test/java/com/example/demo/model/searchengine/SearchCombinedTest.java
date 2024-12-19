package com.example.demo.model.searchengine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchCombinedTest {
    @Test
    void searchISBNreturnOneBook(){

        SearchCombined a = new SearchCombined();
        assertEquals(1,a.combinedSearch("Lee").size());
    }

    @Test
    void searchHasBooksAsInput(){
        SearchCombined a = new SearchCombined();
        assertEquals(20,a.getBookInput().size());
    }
}