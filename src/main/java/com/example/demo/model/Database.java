package com.example.demo.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Database {
    private static String itemsFile = "./src/main/resources/data/books.json";

    // static singleton entity
    private Database() {}

    public static void addBook(Book book) {
        List<LibraryItem> items = loadItems();
        items.add(new LibraryItem(book, 1));
        saveBooks(items);
    }

    public static ArrayList<Book> getBookList() {
        List<LibraryItem> items = loadItems();

        ArrayList<Book> books = new ArrayList<Book>();
        for (LibraryItem item : items) {
            books.add(item.book());
        }

        return books;
    }

    private static List<LibraryItem> loadItems() {
        try {
            List<LibraryItem> items = new ObjectMapper().readValue(
                    new File(itemsFile),
                    new TypeReference<ArrayList<LibraryItem>>() {
                    });

            return items;
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private static void saveBooks(List<LibraryItem> items) {
        try {
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new File(itemsFile), items);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private record LibraryItem(Book book, int copies) {}
}
