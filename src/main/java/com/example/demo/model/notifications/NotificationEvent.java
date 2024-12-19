package com.example.demo.model.notifications;

/**
 * Represents a notification event in the system.
 * - Encapsulates details such as type, associated user ID, message, payload, timestamp, and read status.
 * - Provides constructors for creating notification events and methods for accessing and updating properties.
 * 
 * Key Fields:
 * - `type`: Type of notification (e.g., "Borrow").
 * - `id`: Identifier of the user associated with the event.
 * - `message`: Notification message to display.
 * - `payload`: Additional data related to the event.
 * - `timestamp`: Time when the event was created.
 * - `read`: Indicates whether the notification has been read.
 */

public class NotificationEvent {
    private String type;
    private String id;
    private String message;
    private Object payload;
    private long timestamp;
    private boolean read;

    public NotificationEvent() {
    }

    public NotificationEvent(String type, String id, Object payload, String message) {
        this.type = type;
        this.id = id;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.read = false;
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Object getPayload() {
        return payload;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean getRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}