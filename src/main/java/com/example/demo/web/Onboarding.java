package com.example.demo.web;

import com.example.demo.model.notification.RegistrationNotifier;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Utils;
import com.example.demo.model.Admin;
import com.example.demo.model.LibrarySystem;
import com.example.demo.model.User;
import com.example.demo.model.exceptions.AccountNotFoundException;
import com.example.demo.model.exceptions.IncorrectPasswordException;
import com.example.demo.model.exceptions.PersonExistException;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Onboarding {
    private final LibrarySystem ls;

    public Onboarding(LibrarySystem ls) {
        this.ls = ls;
    }

    @GetMapping("/users")
    public ArrayList<User> onGetUsers() {
        return null;
    }

    @PostMapping("/register")
    public Map<String, Object> onPostRegister(@RequestBody BodyOfRegister body)
            throws AccountNotFoundException, IncorrectPasswordException {
        if (!body.isValid()) { // body not valid
            return Map.of("success", false, "message", "invalid body");
        }
        User u = new User(body.name(), body.email(), body.id(), body.password());
        try {
            ls.getAuth().registerUser(u);
            String token = ls.getAuth().loginUser(u);
            new RegistrationNotifier(u);
            return Map.of("success", true, "token", token);
        } catch (PersonExistException p) {
            return Map.of("success", false, "message", "person already exists");
        }
    }

    @PostMapping("/login")
    public Map<String, Object> onPostLogin(@RequestBody BodyOfLogin body) {
        // Validate the input body
        if (!body.isValid()) {
            return Map.of("success", false, "message", "Invalid body: missing email or password.");
        }

        try {
            // Create a temporary Person object for login purposes
            User tempUser = new User(body.email(), body.password());
            String token = ls.getAuth().loginUser(tempUser);

            return Map.of("success", true, "token", token);
        } catch (AccountNotFoundException e) {
            return Map.of("success", false, "message", "non existent account");
        } catch (IncorrectPasswordException e) {
            return Map.of("success", false, "message", "incorrect password");
        } catch (RuntimeException e) {
            return Map.of("success", false, "message", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/loginAdmin")
    public Map<String, Object> onPostLoginAdmin(@RequestBody BodyOfLoginAdmin body) {
        // Validate the input body
        if (!body.isValid()) {
            return Map.of("success", false, "message", "Invalid body: missing email, password, or key.");
        }

        try {
            // Create a temporary Admin object for login purposes
            Admin tempAdmin = new Admin(body.email(), body.password(), body.adminKey());
            String token = ls.getAuth().loginAdmin(tempAdmin);

            return Map.of("success", true, "token", token);
        } catch (AccountNotFoundException e) {
            return Map.of("success", false, "message", "Admin account does not exist.");
        } catch (IncorrectPasswordException e) {
            return Map.of("success", false, "message", "Incorrect password or key.");
        } catch (RuntimeException e) {
            return Map.of("success", false, "message", "An unexpected error occurred: " + e.getMessage());
        }
    }

    record BodyOfLogin(String email, String password) {
        public boolean isValid() {
            return !Utils.isEmpty(email, password);
        }
    }

    record BodyOfLoginAdmin(String email, String password, String adminKey) {
        public boolean isValid() {
            return !Utils.isEmpty(email, password, adminKey) && adminKey.length() == 4;
        }
    }

    record BodyOfRegister(String email, String password, String id, String name) {
        public boolean isValid() {
            return !Utils.isEmpty(email, password, id, name) && id.length() == 10;
        }

    }
}