package com.example.demo.model;

import org.springframework.stereotype.Service;

import com.example.demo.model.exceptions.BookNotAvailableException;

@Service
public class LoanSystem {
    final LibrarySystem ls;

    public LoanSystem(LibrarySystem ls) {
        this.ls = ls;
    }

    public void borrow(User user, String isbn) throws BookNotAvailableException {
        for (LibraryItem item : Database.getLibraryItems()) {
            // find item
            if (item.book.getISBN().equals(isbn)) {
                if (item.copies > 0) {
                    item.setCopies(item.getCopies() - 1);
                    // can user borrow multiple instances of the same book?? figure it out
                    user.getLoans().add(isbn);
                    Database.updateItem(item);
                    // pass in time to the LoanLog
                    user.getLogs().add(new User.LoanLog(User.LoanLogAction.borrowed, null, isbn));
                    Database.updateUser(user);
                    return;
                } else {
                    // reserve
                }
            }
        }

        // idk but figure it out
        throw new BookNotAvailableException("Book unavailable");
    }

    public void returnBook(User user, String isbn) {
        for (LibraryItem item : Database.getLibraryItems()) {
            // find item
            if (item.book.getISBN().equals(isbn)) {
                item.setCopies(item.getCopies() + 1);

                user.getLoans().remove(isbn);
                Database.updateItem(item);
                user.getLogs().add(new User.LoanLog(User.LoanLogAction.returned, null, isbn));
                Database.updateUser(user);
                return;
            }
        }
        System.out.println("Book should have existed in the database");
    }
}
