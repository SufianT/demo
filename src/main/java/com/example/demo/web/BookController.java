package com.example.demo.web;
import com.example.demo.Utils;
import com.example.demo.model.Book;
import com.example.demo.model.BookHandler;
import com.example.demo.model.Genre;
import com.example.demo.model.LibrarySystem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@RestController
public class BookController {
    private final LibrarySystem ls;
    private final BookHandler bk;

    public BookController(LibrarySystem ls, BookHandler bk) {
        this.ls = ls;
        this.bk=bk;

    }

    @PostMapping("/addBook")
    public Map<String, Object> onPostAddBook(@RequestBody BodyOfAddBook body) {
        if (!body.isValid()) {
            return Map.of("success", false, "message", "Pleas fill in all required fields");
        }

        // Handle multiple authors
        ArrayList<String> authors = new ArrayList<>();
        authors.add(body.author1());
        if (body.author2() != null && !body.author2().isBlank()) {
            authors.add(body.author2());
        }

        // Parse genre and validate
        Genre genreEnum;
        try {
            genreEnum = Genre.fromString(body.genre());
        } catch (IllegalArgumentException e) {
            return Map.of("success", false, "message", "Invalid genre provided");
        }

        // Create book object
        Book book = new Book(body.title(), body.isbn(), authors, false, body.imageURL(), genreEnum, null);

        // Add the book to JSON and the library system
        try {
            bk.saveBookToFile(book,"src/main/resources/static/Database/Books.json");
            //bara för att kolla att det funkar
            ls.library.getBookList().forEach(b -> {
                System.out.println("Title: " + b.getTitle() +
                        ", ISBN: " + b.getISBN() +
                        ", Authors: " + b.getAuthor() +
                        ", Is Borrowed: " + b.getBorrowed());
            });
            return Map.of("success", true, "message", "Book added successfully", "book", book);
        } catch (Exception e) {
            return Map.of("success", false, "message", "Failed to add book: " + e.getMessage());
        }
    }

    @GetMapping("/books")
    public List<Book> getBooks() throws IOException {
        File file = new File("src/main/resources/static/Database/Books.json");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<List<Book>>() {});
    }



}

record BodyOfAddBook(String title, String author1, String author2, String isbn, String imageURL, String genre) {
    boolean isValid() {
        return !Utils.isEmpty(title, author1, isbn, imageURL, genre);
    }
}



/*
    //bara för att kolla att det funkar
            ls.library.getBookList().forEach(b -> {
                System.out.println("Title: " + b.getTitle() +
                        ", ISBN: " + b.getISBN() +
                        ", Authors: " + b.getAuthor() +
                        ", Is Borrowed: " + b.getBorrowed());
            });
 */