package com.example.demo.model.chat;

public class ChatPreview {
    private String id;
    private String name;
    private String lastMessage;

    public ChatPreview(String id, String name, String lastMessage) {
        this.id = id;
        this.name = name;
        this.lastMessage = lastMessage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
