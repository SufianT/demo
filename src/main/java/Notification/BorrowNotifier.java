package Notification;

import com.example.demo.model.User;

public class BorrowNotifier extends Observable{
    public BorrowNotifier(){
        addObserver(new NotificationManager());
    }
    public void notifyBorrow(User user, String isbn) {
        // Create a notification event
        NotificationEvent event = new NotificationEvent("Borrow", user, isbn);
        notifyObservers(event);
    }
}
/*
 BorrowNotifier borrowNotifier = new BorrowNotifier();
                borrowNotifier.notifyBorrow(u, body.isbn());
 */
