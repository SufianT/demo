package com.example.demo.model.searchengine;

import com.example.demo.model.searchengine.StringToWantedWordsInterface;

import java.util.HashMap;
import java.util.Set;

public class StringToWantedWords implements StringToWantedWordsInterface {
    public static HashMap<String, Integer> resultFromSearch = new HashMap<>();

    StringToWantedWords(String string, Set<String> list){


    }

    public HashMap<String, Integer> searchComplete(String string, Set<String> list, int searchAcceptanceLevel){

        HashMap<String,Integer> resultFromSearch = new HashMap<>();
        resultFromSearch.clear();
        String[] words = string.split("\\s+");
        for (String wordSearched : words){
            for (String word : list) {
                int distance = getDistance(wordSearched,word);
                if (distance <= searchAcceptanceLevel){
                    resultFromSearch.putIfAbsent(word,searchAcceptanceLevel-distance);
                    //TODO: This will not get the optimal correct word if it finds a longer path before the shorter.
                    //TODO: Max(something something)

                    //resultFromSearch will give the word and the difference between the
                    //acceptanceLevel and the distance. Meaning that the higher the number the better.
                    //(This will be handy later).
                }
            }
        }

        return resultFromSearch;

    }
    public static int getDistance(String word1, String word2)
    {   /*
            This code should return the shortest distance between two words using
            substitution, addition or deletion. If you want to understand it better
            please watch the video bellow:
            https://www.youtube.com/watch?v=d-Eq6x1yssU
        */
        int lengthWord1 = word1.length();
        int lengthWord2 = word2.length();

        int arr[][] = new int[lengthWord1 + 1][lengthWord2 + 1];

        for (int i = 0; i <= lengthWord1; i++)
            arr[i][0] = i;

        for (int j = 0; j <= lengthWord2; j++)
            arr[0][j] = j;

        for (int i = 1; i <= lengthWord1; i++) {
            char ch1 = word1.charAt(i - 1);

            for (int j = 1; j <= lengthWord2; j++) {
                char ch2 = word2.charAt(j - 1);

                int m = ch1 == ch2 ? 0 : 1;

                arr[i][j] = Math.min(
                        Math.min((arr[i - 1][j] + 1),
                                (arr[i][j - 1] + 1)),
                        arr[i - 1][j - 1] + m);
            }
        }

        return arr[lengthWord1][lengthWord2];
    }
}
