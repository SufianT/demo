package Notification;

import com.example.demo.model.Person;
import com.example.demo.model.User;

public class ChatNotifier extends Observable{
    public ChatNotifier(){
        addObserver(new NotificationManager());
    }
    public void notifyMessage(Person sender, Person receiver, String message) {
        NotificationEvent event = new NotificationEvent("Chat", receiver, message);
        notifyObservers(event); // Notify all listeners about the chat event
    }
}
