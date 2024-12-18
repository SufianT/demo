package com.example.demo.model.Notification;

import com.example.demo.model.User;

public class FineNotifier extends Observable{
    public FineNotifier(User user, String isbn){
        addObserver(new NotificationManager());
        notifyFine(user,isbn);

    }
    public void notifyFine(User user, String isbn) {
        String message="\uD83D\uDD34 Reminder: The book with ISBN " + isbn +
                " is due for return within the next 48 hours. Please return it on time to avoid a fine.";
        // Create a notification event
        NotificationEvent event = new NotificationEvent("Fine", user.getId(), isbn,message);
        notifyObservers(event);
    }
}
