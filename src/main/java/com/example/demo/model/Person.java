package com.example.demo.model;

import java.net.PasswordAuthentication;

public class Person {
    String name;
    String email;
    String password;
    long id;

    public Person(String name, String email, String id, String password) {
    }

    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
}
