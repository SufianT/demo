package com.example.demo.web;

import com.example.demo.model.*;
import com.example.demo.model.notification.NotificationEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class NotificationController {
    Authenticator auth;

    public NotificationController(Authenticator auth) {
        this.auth = auth;
    }

    @GetMapping("/notification")
    public List<NotificationEvent> onGetSaved(@RequestParam String token) {
        Person p = auth.exchange(token);

        if (p instanceof User u) {
            return u.getNotifications();
        }

        return List.of();
    }

    @DeleteMapping("/notification/delete")
    public Map<String, Object> deleteNotification(@RequestParam String token, @RequestParam long timestamp) {
        Person p = auth.exchange(token);

        if (p instanceof User u) {
            // Get the user's notifications
            List<NotificationEvent> notifications = u.getNotifications();

            // Remove the notification matching the timestamp
            boolean removed = notifications.removeIf(n -> n.getTimestamp() == timestamp);

            if (removed) {
                // Update the user in the database after removal
                Database.updateUser(u);
                return Map.of("success", true, "message", "Notification deleted successfully.");
            } else {
                return Map.of("success", false, "message", "Notification not found.");
            }
        }

        // If the person is not a user
        return Map.of("success", false, "message", "Invalid token or user not found.");
    }

    @PatchMapping("/notification/markAsRead")
    public Map<String, Object> markAsRead(@RequestParam String token, @RequestParam long timestamp) {
        Person p = auth.exchange(token);
        if (p instanceof User u) {
            List<NotificationEvent> notifications = u.getNotifications();

            for (NotificationEvent notification : notifications) {
                if (notification.getTimestamp() == timestamp) {
                    notification.setRead(true); // Set the notification as read
                    Database.updateUser(u); // Persist changes to the database
                    return Map.of("success", true, "message", "Notification marked as read.");
                }
            }
            return Map.of("success", false, "message", "Notification not found.");
        }

        return Map.of("success", false, "message", "Invalid token or user.");
    }

    @GetMapping("/notification/hasUnread")
    public Map<String, Object> hasUnreadNotifications(@RequestParam String token) {
        Person p = auth.exchange(token);

        if (p instanceof User u) {
            boolean hasUnread = u.getNotifications().stream().anyMatch(n -> !n.getRead());
            return Map.of("success", true, "hasUnread", hasUnread);
        }

        return Map.of("success", false, "message", "Invalid user");
    }

}
