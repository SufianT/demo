package com.example.demo.model.notification;

import com.example.demo.model.User;

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
