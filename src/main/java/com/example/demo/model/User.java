package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class User extends Person {
    private List<LoanLog> logs;
    private List<String> loans;
    private List<String> saved;

    // No-args constructor for Jackson
    public User() {
        super(null, null, null, null);
    }

    public User(String name, String email, String id, String password) {
        super(name, email, id, password);
        this.logs = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.saved = new ArrayList<>();
    }

    public User(String email, String password) {
        super("DefaultName", email, "DefaultID", password);
    }

    public List<LoanLog> getLogs() {
        return logs;
    }

    public List<String> getLoans() {
        return loans;
    }

    public List<String> getSaved() {
        return saved;
    }

    public enum LoanLogAction {
        borrowed,
        returned,
    }

    public record LoanLog(LoanLogAction action, String time, String isbn) {
    }

    public void setEmail(String email) {
        email = this.getEmail();
    }

    public void setPassword(String password) {
        password = this.getPassword();
    }
}
