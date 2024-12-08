package com.example.demo.web;

import com.example.demo.model.Book;
import com.example.demo.model.BookHandler;
import com.example.demo.model.LibrarySystem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
public class BorrowController {
    private final LibrarySystem ls;
    private final BookHandler bk;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath = "src/main/resources/Database/Books.json";

    public BorrowController(LibrarySystem ls, BookHandler bk) {
        this.ls = ls;
        this.bk = bk;
    }

    @PostMapping("/borrow")
    public Map<String, Object> borrowBook(@RequestBody Map<String, String> requestBody) {
        String isbn = requestBody.get("isbn");
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Load existing books
            ls.getBookList().clear();
            bk.loadBooksFromDatabase(ls,filePath);
            // Find the book by ISBN
            for (Book book : ls.getBookList()) {
                if (book.getISBN().equals(isbn)) {
                    if (book.getBorrowed()) {
                        return Map.of("success", false, "message", "Book is already borrowed.");
                    }
                    // Change the borrowed status
                    book.setBorrowed(true);
                    // Update JSON file
                    mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), ls.getBookList());
                    return Map.of("success", true, "message", "Book borrowed successfully.");
                }
            }
            return Map.of("success", false, "message", "Book not found.");
        } catch (IOException e) {
            return Map.of("success", false, "message", "Error occurred: " + e.getMessage());
        }
    }


    @PostMapping("/return")
    public Map<String, Object> returnbook(@RequestBody Map<String, String> requestBody) {
        String isbn = requestBody.get("isbn");
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Load existing books
            ls.getBookList().clear();
            bk.loadBooksFromDatabase(ls,filePath);

            // Find the book by ISBN
            for (Book book : ls.getBookList()) {
                if (book.getISBN().equals(isbn)) {
                    if (!book.getBorrowed()) {
                        return Map.of("success", false, "message", "Can not return a book that is not borrowed.");
                    }
                    // Change the borrowed status
                    book.setBorrowed(false);
                    // Update JSON file
                    mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), ls.getBookList());
                    return Map.of("success", true, "message", "Book returned successfully.");
                }
            }
            return Map.of("success", false, "message", "Book not found.");
        } catch (IOException e) {
            return Map.of("success", false, "message", "Error occurred: " + e.getMessage());
        }
    }
}
