package com.example.demo.model.Notification;

import com.example.demo.model.Admin;
import com.example.demo.model.Book;
import com.example.demo.model.User;

public class NotificationEvent {
    private String type;        // The type of event (e.g., "Borrow", "Chat").
    private String  id;          // The user involved in the event.
    private String message;     // Additional data (e.g., book or message).
    private Object payload;
    private long timestamp;     // Time of the event.
    private boolean read;

    public NotificationEvent(){}
    // Constructor
    public NotificationEvent(String type, String id,Object payload, String message) {
        this.type = type;
        this.id = id;
        this.message = message;
        this.timestamp = System.currentTimeMillis(); // Automatically set timestamp
        this.read=false;
        this.payload=payload;
    }

    // Getters
    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
    public Object getPayload(){return payload;}

    public long getTimestamp() {
        return timestamp;
    }
    public boolean getRead(){return read;}
    public void setRead(boolean read){this.read=read;}
}