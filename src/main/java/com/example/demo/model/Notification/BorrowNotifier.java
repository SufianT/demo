package com.example.demo.model.notification;

import com.example.demo.model.User;

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
