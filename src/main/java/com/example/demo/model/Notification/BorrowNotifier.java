package com.example.demo.model.notification;

import com.example.demo.model.usermanagement.User;

/**
 * Sends notifications to users when they borrow a book.
 * - Inherits from `Observable` to manage observers and notify them of events.
 * - Automatically creates and notifies a `NotificationEvent` upon
 * instantiation.
 * 
 * Key Methods:
 * - `notifyBorrow`: Notifies observers of a borrow event by creating a
 * `NotificationEvent` with user and book details.
 * 
 * Observers include classes like `NotificationManager` that handle the
 * notification logic.
 */

public class BorrowNotifier extends Observable {
    String message = "You've just borrowed the book with the ISBN: ";

    public BorrowNotifier(User user, String isbn) {
        addObserver(new NotificationManager());
        notifyBorrow(user, isbn);

    }

    public void notifyBorrow(User user, String isbn) {
        // Create a notification event
        NotificationEvent event = new NotificationEvent("Borrow", user.getId(), isbn, message + isbn);
        notifyObservers(event);
    }
}
