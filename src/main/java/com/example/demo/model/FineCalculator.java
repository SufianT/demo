package com.example.demo.model;

public class FineCalculator { // DO not need to store or modify any state
    // Calculates bookfine
    private static int dailyFine = 10;

    public static int calculateFine(int overDueDays) {
        if (overDueDays <= 0) {
            return 0;
        }
        return overDueDays * dailyFine;
    }
}
