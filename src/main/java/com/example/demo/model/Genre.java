package com.example.demo.model;

public enum Genre {
    Fantasy,
    Romance,
    Thriller,
    Sci_fi,
    Horror,
    Mystery,
    Fiction,
    Dystopian,
    Classic,
    Adventure,
    Poetry;
    public static Genre fromString(String value) {
        for (Genre genre : Genre.values()) {
            if (genre.name().equalsIgnoreCase(value)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Invalid genre: " + value);
    }

}
