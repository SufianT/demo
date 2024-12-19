package com.example.demo.model.loansystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Database;
import com.example.demo.model.bookmanager.LibraryItem;
import com.example.demo.model.exceptions.BookNotAvailableException;
import com.example.demo.model.usermanagement.User;

@Service
public class LoanSystem {

    public LoanSystem() {
    }

    public void borrow(User user, String isbn) throws BookNotAvailableException {
        for (LibraryItem item : Database.getLibraryItems()) {
            // find item
            if (item.getBook().getISBN().equals(isbn)) {
                if (item.getCopies() > 0) {
                    item.setCopies(item.getCopies() - 1);
                    user.getLoans().add(isbn);
                    Database.updateItem(item);
                    // pass in time to the LoanLog
                    user.getLogs().add(new User.LoanLog(User.LoanLogAction.borrowed, LocalDate.now().toString(), isbn));
                    Database.updateUser(user);
                    return;
                } else {
                    // reserve for future 
                }
            }
        }

        throw new BookNotAvailableException("Book unavailable");
    }

    public void returnBook(User user, String isbn) {
        for (LibraryItem item : Database.getLibraryItems()) {
            // Find item
            if (item.getBook().getISBN().equals(isbn)) {
                item.setCopies(item.getCopies() + 1);
                user.getLoans().remove(isbn);

                // Find the latest "borrowed" log and update it
                boolean updated = false;
                for (int i = user.getLogs().size() - 1; i >= 0; i--) { // Iterate in reverse to find the latest log
                    User.LoanLog log = user.getLogs().get(i);
                    if (log.isbn().equals(isbn) && log.action() == User.LoanLogAction.borrowed) {
                        user.getLogs().set(i,
                                new User.LoanLog(User.LoanLogAction.returned, LocalDate.now().toString(), isbn));
                        updated = true;
                        break;
                    }
                }

                if (!updated) {
                    System.out.println("No borrowed log found for the given ISBN.");
                }

                Database.updateItem(item);
                Database.updateUser(user);
                return;
            }
        }
        System.out.println("Book should have existed in the database");
    }
    public List<String> getBooksDueInTwoDays(User user) {
        List<String> booksDueSoon = new ArrayList<>();
        LoanPolicy policy = new BookLoanPolicy();

        for (int i = 0; i < user.getLoans().size(); i++) {
            for (int j = user.getLogs().size() - 1; j >= 0; j--) {
                if (user.getLoans().get(i).equals(user.getLogs().get(j).isbn())) {
                    LoanPeriod period = new LoanPeriod(LoanPeriod.parseDate(user.getLogs().get(j).time()), policy);
                    LocalDate dueDate = period.getEndDate();
                    int daysUntilDue = (int) java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), dueDate);

                    // Check if the book is due in 2 days or less
                    if (daysUntilDue <= 2 && daysUntilDue >0) {
                        if (!booksDueSoon.contains(user.getLoans().get(i))) {
                            booksDueSoon.add(user.getLoans().get(i)); // Add the ISBN to the list only if it's not already present
                        }
                    }
                    break; // Stop searching once we find the most recent log for this loan
                }
            }
        }

        return booksDueSoon; // Return the list of books due in 2 days
    }



}
