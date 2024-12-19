package com.example.demo.web;

import com.example.demo.model.*;
import com.example.demo.model.chat.ChatManager;
import com.example.demo.model.chat.ChatMessageRequest;
import com.example.demo.model.chat.ChatPreview;
import com.example.demo.model.usermanagement.Admin;
import com.example.demo.model.usermanagement.Authenticator;
import com.example.demo.model.usermanagement.Person;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/chat")
public class ChatController {
    private final ChatManager chatManager;
    private final Authenticator authenticator;

    public ChatController(ChatManager chatManager, Authenticator authenticator) {
        this.chatManager = chatManager;
        this.authenticator = authenticator;
    }

    @GetMapping("/user-role")
    public String getUserRole(@RequestParam String token) {
        Person user = authenticator.exchange(token);

        if (user instanceof Admin) {
            return "admin";
        } else {
            return "user";
        }
    }

    // Fetch all messages for the currently logged-in user
    @GetMapping("/messages")
    public List<String> getMessages(@RequestParam String token) {
        Person user = authenticator.exchange(token);

        if (user == null) {
            throw new IllegalArgumentException("Invalid token.");
        }

        return chatManager.getMessages(user);
    }

    // Fetch a list of all chats available to the admin
    @GetMapping("/admin-chats")
    public List<ChatPreview> getAdminChats(@RequestParam String token) {
        Person user = authenticator.exchange(token);
        if (!(user instanceof Admin)) {
            throw new IllegalArgumentException("Only admins can access this endpoint.");
        }

        return chatManager.getAdminChats();
    }

    @GetMapping("/admin-chat-messages")
    public List<String> adminGetMessages(@RequestParam String token, @RequestParam String userId) {
        Person admin = authenticator.exchange(token);
        if (!(admin instanceof Admin)) {
            throw new IllegalArgumentException("Only admins can access this endpoint.");
        }

        Person user = Database.findUserById(userId);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return chatManager.getMessages(user);
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody ChatMessageRequest request, @RequestParam String token) {
        Person sender = authenticator.exchange(token);

        if (sender == null) {
            throw new IllegalArgumentException("Invalid sender token.");
        }

        chatManager.addMessageFromUser(sender, request.getMessage());
    }

    @PostMapping("/send-admin")
    public void sendMessage(@RequestBody ChatMessageRequest request, @RequestParam String token, @RequestParam String recipient) {
        Person sender = authenticator.exchange(token);
        Person receiver = Database.findUserById(recipient);

        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Invalid sender or receiver token.");
        }

        chatManager.addMessage(sender, receiver, request.getMessage());
    }
}

