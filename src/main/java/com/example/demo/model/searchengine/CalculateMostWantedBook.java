package com.example.demo.model.searchengine;

import com.example.demo.model.Book;

import java.util.*;

public class CalculateMostWantedBook implements CalculateMostWantedBookInterface {


    /*
    This calculates which books are most relevant to the search.
    */
    public HashMap<Book, Integer> sortBooksFromSearch(HashMap<String, Integer> hashMap, ArrayList<Book> books) {
        Iterator<String> iterator = hashMap.keySet().iterator();
        HashMap<Book, Integer> booksThatWillShow = new HashMap<>();
        for (Book book : books) {
            int n = 0;

            while (iterator.hasNext()) {
                String key = iterator.next();
                if ((" " + book.getTitle().toLowerCase() + " ").contains(" " + key + " ")) {
                    n += hashMap.get(key);
                }
                if ((" " + book.getAuthors().toString().toLowerCase() + " ").contains(" " + key + " ")) {
                    n += hashMap.get(key);
                }

            }
            if (n > 0) {
                booksThatWillShow.putIfAbsent(book, n);
            }
        }
        return booksThatWillShow;
    }

    ;



    public ArrayList<Book> sortHashMapByValue(HashMap<Book, Integer> map) {
        // Create a list from the entries of the HashMap
        List<Map.Entry<Book, Integer>> entryList = new ArrayList<>(map.entrySet());

        // Sort the list based on the values
        entryList.sort((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));

        // Create an ArrayList to store the sorted keys
        ArrayList<Book> sortedKeys = new ArrayList<>();

        // Add keys from the sorted entry list to the ArrayList
        for (Map.Entry<Book, Integer> entry : entryList) {
            sortedKeys.add(entry.getKey());
        }

        //IF the array is big enough we should reverse its order.
        if(sortedKeys.size()>1) {
            ArrayList<Book> revArrayList = new ArrayList<>();
            for (int i = sortedKeys.size() - 1; i >= 0; i--) {

                // Append the elements in reverse order
                revArrayList.add(sortedKeys.get(i));
            }
            sortedKeys=revArrayList;
        }
        return sortedKeys;
    }
}