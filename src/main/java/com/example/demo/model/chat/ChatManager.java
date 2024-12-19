package com.example.demo.model.chat;

import com.example.demo.model.Database;
import com.example.demo.model.usermanagement.Admin;
import com.example.demo.model.usermanagement.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChatManager {
    private final File messageFile = new File("src/main/resources/data/Chat.json");
    private final Map<String, List<String>> messages = new HashMap<>(); // Maps userId -> List of messages
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ChatManager() {
        loadMessages();
    }

    // Adds a message from admin to user
    public void addMessage(Person sender, Person receiver, String message) {
        if (!(sender instanceof Admin)) throw new IllegalArgumentException("Sender must be admin");

        String formattedMessage = "Admin: " + message;

        messages.computeIfAbsent(receiver.getId(), k -> new ArrayList<>()).add(formattedMessage);
        saveMessages();
    }

    // Adds a message from user to admin
    public void addMessageFromUser(Person sender, String message) {
        String formattedMessage = sender.getName() + ": " + message;

        messages.computeIfAbsent(sender.getId(), k -> new ArrayList<>()).add(formattedMessage);
        saveMessages();
    }

    // Retrieves all messages for a specific user
    public List<String> getMessages(Person user) {
        return messages.getOrDefault(user.getId(), new ArrayList<>());
    }

    // Retrieves a list of admin chat previews
    public List<ChatPreview> getAdminChats() {
        List<ChatPreview> chatPreviews = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : messages.entrySet()) {
            String userId = entry.getKey();
            List<String> chatMessages = entry.getValue();

            String lastMessage = chatMessages.isEmpty() ? "" : chatMessages.get(chatMessages.size() - 1);
            Person user = Database.findUserById(userId); // Retrieve user info from the database
            if (user != null) {
                chatPreviews.add(new ChatPreview(user.getId(), user.getName(), lastMessage));
            }
        }
        return chatPreviews;
    }

    private void loadMessages() {
        if (messageFile.exists()) {
            try {
                Map<String, List<String>> loadedMessages = objectMapper.readValue(
                        messageFile,
                        new TypeReference<>() {}
                );
                messages.putAll(loadedMessages);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveMessages() {
        try {
            objectMapper.writeValue(messageFile, messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
