package com.example.demo.web;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Utils;
import com.example.demo.model.LibrarySystem;
import com.example.demo.model.User;
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
    public Map<String, Object> onPostRegister(@RequestBody BodyOfRegister body) {
        if (!body.isValid()) {
            return Map.of("success", false, "message", "invalid body");
        }
        User u = new User(body.name(), body.email(), body.id(), body.password());
        try {
            ls.getAuth().registerUser(u);
            String token = ls.getAuth().login(u);
            return Map.of("success", true, "token", token);
        } catch (PersonExistException p) {
            return Map.of("success", false, "message", "person already exists");
        }
    }

    @PostMapping("/login2")
    public Map<String, Object> onPostLogin(@RequestBody BodyOfRegister body) {
        if (!body.isValid()) {
            return Map.of("success", false, "message", "invalid body");
        }

        return Map.of("success", true);
    }

}

record BodyOfRegister(String email, String password, String id, String name) {
    boolean isValid() {
        return !Utils.isEmpty(email, password, id, name) && id.length() == 10;
    }

}
