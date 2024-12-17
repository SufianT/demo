package com.example.demo.model.Notification;

import com.example.demo.model.User;

public class FineNotifier extends Observable{
    String message="this book xxxx should be returend in 24 houres otherwaies u will get fined ";
    public FineNotifier(User user, String isbn){
        addObserver(new NotificationManager());
        notifyFine(user,isbn);

    }
    public void notifyFine(User user, String isbn) {
        // Create a notification event
        NotificationEvent event = new NotificationEvent("Fine", user.getId(), isbn,message);
        notifyObservers(event);
    }
}
