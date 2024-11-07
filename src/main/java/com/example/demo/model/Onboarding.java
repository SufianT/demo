package com.example.demo.model;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Utils;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Onboarding {

    @GetMapping("/users")
    public ArrayList<User> onGetUsers() {
        return null;
    }

    @PostMapping("/login2")
    public Map<String, Object> onPostLogin(@RequestBody BodyOfRegister body) {
        if (!body.isValid()) {
            return Map.of("success", false, "message", "invalid body");
        }

        return Map.of("success", true);
    }

}

record BodyOfRegister(String email, String password) {
    boolean isValid() {
        return !Utils.isEmpty(email) && !Utils.isEmpty(password);
    }

}
