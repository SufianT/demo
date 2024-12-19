package com.example.demo.model.notification;

import com.example.demo.model.Database;
import com.example.demo.model.usermanagement.Person;
import com.example.demo.model.usermanagement.User;

public class NotificationManager implements Observer {

    @Override
    public void update(NotificationEvent event) {

        Person p = Database.getPersonById(event.getId());
        if (p instanceof User user) {
            // Add the notification to the user
            user.getNotifications().add(event);

            // Persist the updated user object in the database
            Database.updateUser(user);

            System.out.println("Notification added for user: " + user.getName());
        }
    }

}
