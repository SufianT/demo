package com.example.demo.model.searchengine;

import com.example.demo.model.Book;

import java.util.*;

public class CalculateMostWantedBook implements CalculateMostWantedBookInterface {


    /*
    This calculates which books are most relevant to the search.
    */
    public HashMap<Book, Integer> sortBooksFromSearch(HashMap<String, Integer> hashMap, ArrayList<Book> books) {
        HashMap<Book, Integer> bookScores = new HashMap<>();

        // Iterate through the list of books
        for (Book book : books) {
            int sum = 0; // Initialize the sum for the current book

            // Convert the Book object to String using Book.toString()
            String bookContent = " "+book.toString().toLowerCase()+" ";

            // Check each word in the HashMap
            for (String word : hashMap.keySet()) {
                if (bookContent.contains(" "+word+" ")) {
                    // Add the score associated with the word
                    sum += hashMap.get(word);
                }
            }

            // Add the book and its calculated score to the result HashMap
            if(sum!=0) {
                bookScores.put(book, sum);
            }
        }
        // Return the resulting HashMap
        return bookScores;

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