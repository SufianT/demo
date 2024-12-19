package com.example.demo.model.searchengine;

import java.util.HashMap;
import java.util.Set;

public interface StringToWantedWordsInterface {
    public HashMap<String, Integer> searchComplete(String string, Set<String> list, int searchAcceptanceLevel);
}