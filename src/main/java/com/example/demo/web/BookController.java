package com.example.demo.web;

import com.example.demo.Utils;
import com.example.demo.model.Book;
import com.example.demo.model.Genre;
import com.example.demo.model.LibrarySystem;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
public class BookController {
    private final LibrarySystem ls;

    public BookController(LibrarySystem ls) {
        this.ls = ls;
    }

    @PostMapping("/addBook")
    public Map<String, Object> onPostAddBook(@RequestBody BodyOfAddBook body) {
        if (!body.isValid()) {
            return Map.of("success", false, "message", "Please fill in all the required fields");
        }

        // Handle multiple authors
        List<String> authors = List.of(body.author1());
        if (!Utils.isEmpty(body.author2())) {
            authors.add(body.author2());
        }

        // Create book object & save
        Book book = new Book(body.title(), body.isbn(), authors, body.imageURL(), body.genre());
        ls.addBook(book);

        System.out.println("LOG: Added book!");
        return Map.of("success", true);
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return ls.getBookList();
    }

    record BodyOfAddBook(String title, String author1, String author2, String isbn, String imageURL, Genre genre) {
        boolean isValid() {
            return !Utils.isEmpty(title, author1, isbn, imageURL) && genre != null;
        }
    }
}
