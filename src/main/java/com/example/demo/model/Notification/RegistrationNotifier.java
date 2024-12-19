package com.example.demo.model.notification;

import com.example.demo.model.usermanagement.User;

/**
 * Sends a welcome notification to a newly registered user.
 * - Inherits from `Observable` to manage observers and notify them of registration events.
 * - Automatically creates and sends a `NotificationEvent` upon instantiation.
 * 
 * Key Methods:
 * - `notifyReg`: Generates a personalised welcome message and notifies observers of the registration event.
 * 
 * Observers include classes like `NotificationManager` that handle the delivery of the notification.
 */

public class RegistrationNotifier extends Observable {

    public RegistrationNotifier(User user) {
        addObserver(new NotificationManager());
        notifyReg(user);
    }

    public void notifyReg(User user) {
        String message = "🎉 Welcome to BookHaven, " + user.getName() +
                "! We're thrilled to have you join our community of book lovers. " +
                "Start exploring our vast collection of books today, and enjoy the journey into the world of stories and knowledge. Happy reading! 📚";
        NotificationEvent event = new NotificationEvent("Welcome", user.getId(), user.getName(), message);
        notifyObservers(event);

    }
}
