package com.example.demo.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Handles persistent storage for books using JSON files.
 * Responsible for loading, saving, and managing the stored data.
 */

public class Database {
    private final static String itemsFile = "./src/main/resources/data/books.json";
    private final static String usersFile = "./src/main/resources/data/users.json";
    private final static String adminsFile = "./src/main/resources/data/admins.json";

    // static singleton entity
    private Database() {
    }

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

    // User Methods
    public static User findUser(String email, String password) {
        ArrayList<User> users = loadFromFile(usersFile, new TypeReference<List<User>>() {
        });
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static boolean addUser(User user) {
        ArrayList<User> users = loadFromFile(usersFile, new TypeReference<List<User>>() {
        });
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                return false; // User already exists
            }
        }
        users.add(user);
        saveToFile(usersFile, users);
        return true;
    }

    // Admin Methods
    public static Admin findAdmin(String email, String password, String adminKey) {
        ArrayList<Admin> admins = loadFromFile(adminsFile, new TypeReference<List<Admin>>() {
        });
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)
                    && admin.getAdminKey().equals(adminKey)) {
                return admin;
            }
        }
        return null;
    }

    // Helpers
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

    private static <T> ArrayList<T> loadFromFile(String filePath, TypeReference<List<T>> typeReference) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return new ArrayList<>(new ObjectMapper().readValue(file, typeReference));
            }
        } catch (IOException e) {
            System.err.println("Error loading data from " + filePath + ": " + e.getMessage());
        }
        return new ArrayList<>();
    }

    private static <T> void saveToFile(String filePath, ArrayList<T> data) {
        try {
            new ObjectMapper().writeValue(new File(filePath), data);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving data to " + filePath);
        }
    }

    private record LibraryItem(Book book, int copies) {
    }
}
