package com.example.demo.model;

import java.security.PublicKey;

public class Admin extends Person{
static Authenticator authenticator;

    public Admin(String name, String email, String id, String password) {
        super(name, email, id, password);
    }
}
