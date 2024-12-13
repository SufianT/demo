package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.exceptions.BookNotAvailableException;
@Service
public class LoanSystem {
    final LibrarySystem ls;

    HashMap<String, List<BorrowedBook>> borrowList;

    public LoanSystem(LibrarySystem ls) {
        this.ls = ls;
        borrowList = new HashMap<>();
    }

    public void borrow(LibraryItem item, LoanPeriod period, User user) throws BookNotAvailableException {
        if (ls.isAvailable(item.getBook())) {
            item.setCopies(item.getCopies() - 1);
            borrowList.computeIfAbsent(user.getId(), k -> new ArrayList<>())
                    .add(new BorrowedBook(item.getBook().getISBN(), period));
            addBookToList(item.getBook(), user);
        }
        throw new BookNotAvailableException("Book unavailable");
    }

    public void addBookToList(Book book, User user) {
        user.getBookList().add(book);

    }

    public record BorrowedBook(String isbn, LoanPeriod period) {

    }

}
