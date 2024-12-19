package com.example.demo.model;

import com.example.demo.model.bookmanager.Book;
import com.example.demo.model.bookmanager.LibraryItem;
import com.example.demo.model.usermanagement.Admin;
import com.example.demo.model.usermanagement.Person;
import com.example.demo.model.usermanagement.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        saveItems(items);
    }

    public static List<LibraryItem> getLibraryItems() {
        List<LibraryItem> items = loadItems();

        return items;
    }

    public static ArrayList<Book> getBookList() {
        List<LibraryItem> items = loadItems();

        ArrayList<Book> books = new ArrayList<Book>();
        for (LibraryItem item : items) {
            books.add(item.getBook());
        }

        return books;
    }

    // User Methods
    public static User findUser(String email) {
        List<User> users = loadFromFile(usersFile, new TypeReference<ArrayList<User>>() {
        });
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getId().equals(user.getId()))
                return user;
        }
        return null;
    }

    public static User findUserById(String id) {
        ArrayList<User> users = loadFromFile(usersFile, new TypeReference<ArrayList<User>>() {
        });
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public static boolean addUser(User user) {
        List<User> users = loadFromFile(usersFile, new TypeReference<ArrayList<User>>() {
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
    public static Person getPersonById(String userId) {
        List<User> users = loadFromFile(usersFile, new TypeReference<ArrayList<User>>() {});
        List<Admin> admins = loadFromFile(adminsFile, new TypeReference<ArrayList<Admin>>() {});

        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user; // Return the matching user
            }
        }
        for (Admin admin: admins) {
            if (admin.getId().equals(userId)) {
                return admin; // Return the matching user
            }
        }
        return null; // If no user is found
    }

    public static void updateUser(User user) {
        List<User> users = loadFromFile(usersFile, new TypeReference<ArrayList<User>>() {
        });
        for (int i = 0; i < users.size(); i++) {
            // find user
            if (users.get(i).getEmail().equals(user.getEmail())) {
                users.set(i, user);
                saveToFile(usersFile, users);
                return;
            }
        }
        System.out.println("updateUser() -> user not found");
    }
    public static boolean hasNotification(String userId, String type, String payload) {
        List<User> users = loadFromFile(usersFile, new TypeReference<ArrayList<User>>() {
        });        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user.getNotifications().stream()
                        .anyMatch(n -> n.getType().equals(type) && n.getPayload().equals(payload));
            }
        }
        return false;
    }

    // Admin Methods
    public static Admin findAdmin(String email, String password, String adminKey) {
        List<Admin> admins = loadFromFile(adminsFile, new TypeReference<ArrayList<Admin>>() {
        });
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)
                    && admin.getAdminKey().equals(adminKey)) {
                return admin;
            }
        }
        return null;
    }

    public static Admin findAdminById(String id) {
        List<Admin> admins = loadFromFile(adminsFile, new TypeReference<ArrayList<Admin>>() {
        });
        for (Admin admin : admins) {
            if (admin.getId().equals(id)) {
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

    public static void updateItem(LibraryItem item) {
        ArrayList<LibraryItem> items = loadFromFile(itemsFile, new TypeReference<ArrayList<LibraryItem>>() {
        });
        for (int i = 0; i < items.size(); i++) {
            // find item
            if (items.get(i).getBook().equals(item.getBook())) {
                items.set(i, item);
                saveToFile(itemsFile, items);
                return;
            }
        }
        System.out.println("updateItem() -> item not found");
    }

    private static void saveItems(List<LibraryItem> items) {
        try {
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new File(itemsFile), items);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private static <T> ArrayList<T> loadFromFile(String filePath, TypeReference<ArrayList<T>> typeReference) {
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

    private static <T> void saveToFile(String filePath, List<T> data) {
        try {
            new ObjectMapper().writeValue(new File(filePath), data);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving data to " + filePath);
        }
    }



}
