package com.example.demo.model.Notification;

import com.example.demo.model.User;

public class RegistrationNotifier extends Observable {
    String message="Welcome ";

    public RegistrationNotifier(User user){
        addObserver(new NotificationManager());
        notifyReg(user);
    }
    public void notifyReg(User user) {
        NotificationEvent event = new NotificationEvent("Welcome", user.getId(), user.getName(),message);
        notifyObservers(event);

    }
}
