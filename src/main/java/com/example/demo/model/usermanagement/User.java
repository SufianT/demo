package com.example.demo.model.usermanagement;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.notification.NotificationEvent;

/**
 * Represents a user in the system, extending the `Person` class.
 * - Includes additional attributes for tracking loans, saved items,
 * notifications, and loan logs.
 * - Provides constructors for creating user instances with varying levels of
 * detail.
 * 
 * Key Attributes:
 * - `logs`: A list of loan logs recording actions like borrowing or returning
 * books.
 * - `loans`: A list of currently borrowed book ISBNs.
 * - `saved`: A list of saved book ISBNs for future reference.
 * - `notifications`: A list of notification events associated with the user.
 * 
 * Key Methods:
 * - `getLogs`: Retrieves the user's loan logs.
 * - `getLoans`: Retrieves the list of currently borrowed books.
 * - `getSaved`: Retrieves the list of saved books.
 * - `getNotifications`: Retrieves the list of notifications for the user.
 * 
 * Nested Types:
 * - `LoanLogAction`: Enum defining actions such as `borrowed` and `returned`.
 * - `LoanLog`: Record to log loan actions with action type, timestamp, and
 * ISBN.
 */

public class User extends Person {
    private List<LoanLog> logs;
    private List<String> loans;
    private List<String> saved;
    private List<NotificationEvent> notifications;

    // No-args constructor for Jackson
    public User() {
        super(null, null, null, null);
    }

    public User(String name, String email, String id, String password) {
        super(name, email, id, password);
        this.logs = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.saved = new ArrayList<>();
        this.notifications = new ArrayList<NotificationEvent>();
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

    public List<NotificationEvent> getNotifications() {
        return notifications;
    }

    public enum LoanLogAction {
        borrowed,
        returned,
    }

    public record LoanLog(LoanLogAction action, String time, String isbn) {
    }
}
