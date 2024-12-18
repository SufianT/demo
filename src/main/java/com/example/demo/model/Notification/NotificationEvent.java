package com.example.demo.model.Notification;

import com.example.demo.model.Admin;
import com.example.demo.model.Book;
import com.example.demo.model.User;

public class NotificationEvent {
    private String type;
    private String  id;
    private String message;
    private Object payload;
    private long timestamp;
    private boolean read;

    public NotificationEvent(){}

    public NotificationEvent(String type, String id,Object payload, String message) {
        this.type = type;
        this.id = id;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.read=false;
        this.payload=payload;
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
    public Object getPayload(){return payload;}

    public long getTimestamp() {
        return timestamp;
    }
    public boolean getRead(){return read;}
    public void setRead(boolean read){this.read=read;}
}