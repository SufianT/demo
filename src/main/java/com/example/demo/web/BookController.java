package com.example.demo.web;

import com.example.demo.Utils;
import com.example.demo.model.Book;
import com.example.demo.model.Genre;
import com.example.demo.model.LibrarySystem;
import com.example.demo.model.exceptions.IsbnAlreadyExistException;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController {
    private final LibrarySystem ls;

    public BookController(LibrarySystem ls) {
        this.ls = ls;
    }

    @PostMapping("/addBook")
    public Map<String, Object> onPostAddBook(@RequestBody BodyOfAddBook body)throws IsbnAlreadyExistException{
        if (!body.isValid()) {
            return Map.of("success", false, "message", "Please fill in all the required fields");
        }

        // Handle multiple authors
        List<String> authors = List.of(body.author1());
        if (!Utils.isEmpty(body.author2())) {
            authors.add(body.author2());
        }

        Optional<Book> existingBook = ls.getBookList().stream()
        .filter(book -> book.getISBN().equals(body.isbn()))
        .findFirst();

        if (existingBook.isPresent()) {
        return Map.of("success", false, "message", "A book with this ISBN already exists.");
        }
        

     
    

        // Create book object & save
        Book book = new Book(body.title(), body.isbn(), authors, body.imageURL(), body.genre());
        ls.addBook(book);

        return Map.of("success", true, "message", "Book added successfully", "book", book);

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
