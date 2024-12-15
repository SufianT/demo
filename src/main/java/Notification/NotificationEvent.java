package Notification;

import com.example.demo.model.Person;
import com.example.demo.model.User;

public class NotificationEvent {
    private String type;        // The type of event (e.g., "Borrow", "Chat").
    private Person person;          // The user involved in the event.
    private Object payload;     // Additional data (e.g., book or message).
    private long timestamp;     // Time of the event.

    // Constructor
    public NotificationEvent(String type, Person person, Object payload) {
        this.type = type;
        this.person = person;
        this.payload = payload;
        this.timestamp = System.currentTimeMillis(); // Automatically set timestamp
    }

    // Getters
    public String getType() {
        return type;
    }

    public Person getPerson() {
        return person;
    }

    public Object getPayload() {
        return payload;
    }

    public long getTimestamp() {
        return timestamp;
    }
}