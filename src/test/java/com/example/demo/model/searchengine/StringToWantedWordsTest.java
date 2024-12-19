package com.example.demo.model.searchengine;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StringToWantedWordsTest {
    @Test
    void getDistance123456till1234_2True(){
        Set<String> set = new HashSet<String>();
        set.add("1234");
        StringToWantedWords stringToWantedWords = new StringToWantedWords();
        assertEquals(false,stringToWantedWords.searchComplete("123456",set,2).isEmpty());
    }
    @Test
    void getDistance123456till1234_1False(){
        Set<String> set = new HashSet<String>();
        set.add("1234");
        StringToWantedWords stringToWantedWords = new StringToWantedWords();
        assertEquals(true,stringToWantedWords.searchComplete("123456",set,1).isEmpty());
    }

    /*
    @Test
    void getDistance123456till1234_value(){
        Set<String> set = new HashSet<String>();
        set.add("1234");
        StringToWantedWords stringToWantedWords = new StringToWantedWords();
        assertEquals(1,stringToWantedWords.searchComplete("123456",set,2).values().getIndex(0));
    }*/

}