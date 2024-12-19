package com.example.demo.model.notifications;

import com.example.demo.model.Database;
import com.example.demo.model.usermanagement.Person;
import com.example.demo.model.usermanagement.User;

/**
 * Handles the processing and delivery of notifications for users.
 * - Implements the `Observer` interface to respond to notification events.
 * - Updates the user's notification list and persists the changes in the
 * database.
 * 
 * Key Method:
 * - `update`: Processes a `NotificationEvent` by adding it to the corresponding
 * user's notifications and saving the changes.
 * 
 * Works with the `Database` to fetch and update user information.
 */

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
