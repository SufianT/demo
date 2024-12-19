package com.example.demo.model.usermanagement;

/**
 * Represents a generic person in the system.
 * - Serves as a base class for different types of users, such as `User` and
 * `Admin`.
 * - Stores common attributes like name, email, password, and a unique ID.
 * 
 * Key Methods:
 * - `getName`: Retrieves the person's name.
 * - `getEmail`: Retrieves the person's email address.
 * - `getPassword`: Retrieves the person's password.
 * - `getId`: Retrieves the person's unique ID.
 * 
 * Intended to be extended by specific user types to add additional
 * functionality.
 */

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
