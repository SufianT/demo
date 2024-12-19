package com.example.demo.model.usermanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an admin user in the system, extending the `Person` class.
 * - Includes an additional attribute, `adminKey`, used to authenticate or authorise admin-specific actions.
 * - Supports multiple constructors for creating admin instances with varying levels of detail.
 * 
 * Key Methods:
 * - `getAdminKey`: Retrieves the admin-specific key.
 * 
 * Annotations:
 * - `@JsonIgnoreProperties`: Ignores unknown properties during JSON deserialization.
 * - `@JsonProperty`: Maps the `adminKey` field for JSON serialization/deserialization.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Admin extends Person {

    @JsonProperty("adminKey")
    private String adminKey;

    public Admin(String name, String email, String id, String password, String adminKey) {
        super(name, email, id, password);
        this.adminKey = adminKey;
    }

    public Admin(String email, String password, String adminKey) {
        super("DefaultName", email, "DefaultID", password);
        this.adminKey = adminKey;
    }

    public Admin() {
        super("DefaultName", "DefaultEmail", "DefaultID", "DefaultPassword");
        this.adminKey = "DefaultKey";
    }

    public String getAdminKey() {
        return this.adminKey;
    }
}
