package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.stereotype.Service;

import com.example.demo.model.exceptions.AccountNotFoundException;
import com.example.demo.model.exceptions.IncorrectPasswordException;
import com.example.demo.model.exceptions.PersonExistException;

@Service
public class Authenticator implements AuthenticationInterface {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Admin> admins = new ArrayList<>();
    HashMap<String, Person> tokens = new HashMap<>();

    private final String USERS_FILE = "./src/main/resources/data/users.json";
    private final String ADMINS_FILE = "./src/main/resources/data/admins.json";

    public Authenticator() {
        initialize();
    }

    // Only required to call once at the start of the application
    private void initialize() {
        admins = loadFromFile(ADMINS_FILE, new TypeReference<List<Admin>>() {
        });
        users = loadFromFile(USERS_FILE, new TypeReference<List<User>>() {
        });
    }

    @Override
    public String loginUser(User user) throws AccountNotFoundException, IncorrectPasswordException {
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                if (u.getPassword().equals(user.getPassword())) {
                    // Generate a new token for the session
                    UUID uuid = UUID.randomUUID();
                    tokens.put(uuid.toString(), u);
                    return uuid.toString();
                } else {
                    throw new IncorrectPasswordException();
                }
            }
        }
        throw new AccountNotFoundException();
    }

    @Override
    public String loginAdmin(Admin admin) throws AccountNotFoundException, IncorrectPasswordException {
        for (Admin a : admins) {
            if (a.getEmail().equals(admin.getEmail())) {
                if (a.getPassword().equals(admin.getPassword()) && a.getAdminKey().equals(admin.getAdminKey())) {
                    // Generate a new token for the session
                    UUID uuid = UUID.randomUUID();
                    tokens.put(uuid.toString(), a);
                    return uuid.toString();
                } else {
                    throw new IncorrectPasswordException();
                }
            }
        }
        throw new AccountNotFoundException();
    }

    @Override
    public void logout(String uuid) {
        tokens.remove(uuid);
    }

    @Override
    public void registerUser(User user) throws PersonExistException {
        for (User u : users) {
            if (u.getId().equals(user.getId()) || u.getEmail().equals(user.getEmail())) {
                throw new PersonExistException();
            }
        }
        users.add(user);
        saveToFile(USERS_FILE, users);
    }

    @Override
    public void registerAdmin(Admin admin) throws PersonExistException {
        throw new UnsupportedOperationException("Admins cannot be registered via the web interface.");
    }

    public Person exchange(String uuid) {
        return tokens.get(uuid);
    }

    private <T> ArrayList<T> loadFromFile(String filePath, TypeReference<List<T>> typeReference) {
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

    private <T> void saveToFile(String filePath, ArrayList<T> data) {
        try {
            new ObjectMapper().writeValue(new File(filePath), data);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving data to " + filePath);
        }
    }
}
