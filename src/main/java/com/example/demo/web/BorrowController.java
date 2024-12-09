package com.example.demo.web;

import com.example.demo.model.LibrarySystem;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowController {
    private final LibrarySystem ls;

    public BorrowController(LibrarySystem ls) {
        this.ls = ls;
    }

    /* @PostMapping("/borrow")
    public Map<String, Object> borrowBook(@RequestBody Map<String, String> requestBody) {
        String isbn = requestBody.get("isbn");

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
                    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new File(filePath), ls.getBookList());
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
    } */
}
