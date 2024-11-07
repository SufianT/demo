package com.example.demo;

public class Utils {
    private Utils() {}

    public static boolean isEmpty(String... strings) {
        for (String str : strings) {
            if (str == null || str.isEmpty()) return true;
        }
        return false;
    }

    public static boolean isOneOf(String s, String... strings) {
        for (String str : strings) {
            if (s.equals(str)) return true;
        }
        return false;
    }
}

