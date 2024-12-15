package Notification;

import com.example.demo.model.User;

public class RegistrationNotifier extends Observable {
    public RegistrationNotifier(){
        addObserver(new NotificationManager());
    }
    public void notifyUser(User user, String message) {
        // Implement notification logic here
        NotificationEvent event = new NotificationEvent("Registration", user, message);
        notifyObservers(event);
        System.out.println( message + " for user " + user.getName());
    }
}
