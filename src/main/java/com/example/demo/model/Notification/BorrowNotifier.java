package com.example.demo.model.Notification;

import com.example.demo.model.Database;
import com.example.demo.model.User;

public class BorrowNotifier extends Observable{
    String message="You borrowed a book with ISBN: ";
    public BorrowNotifier(User user, String isbn){
        addObserver(new NotificationManager());
        notifyBorrow(user,isbn);

    }
    public void notifyBorrow(User user, String isbn) {
        // Create a notification event
        NotificationEvent event = new NotificationEvent("Borrow", user.getId(), isbn,message);
        notifyObservers(event);
    }
}
/*
 BorrowNotifier borrowNotifier = new BorrowNotifier();
                borrowNotifier.notifyBorrow(u, body.isbn());
 */
