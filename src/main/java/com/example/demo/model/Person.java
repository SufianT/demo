package com.example.demo.model;

public abstract class Person {
    private String name;
    private String email;
    private String password;
    private String id;

    // Constructor
    public Person(String name, String email, String id, String password) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }
}
