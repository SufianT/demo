package Notification;

import com.example.demo.model.Book;

public class NotificationManager implements Observer {
    @Override
    public void update(NotificationEvent event) {
        if ("Borrow".equals(event.getType())) {
            System.out.println("Notification: " + event.getPerson().getName() +
                    " borrowed " + ((String) event.getPayload()));
        } else if ("Chat".equals(event.getType())) {
            System.out.println("Chat Event: Message from " + event.getPerson().getName() +
                    ": " + event.getPayload());
        }else if ("Registration".equals(event.getType())) {
            System.out.println("Chat Event: Message from " + event.getPerson().getName() +
                    ": " + event.getPayload());
        }


    }
}
