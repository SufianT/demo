package com.example.demo.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BookHandler {
     LibrarySystem librarySystem;

    public BookHandler(LibrarySystem librarySystem) {
        this.librarySystem=librarySystem;
    }
    public void loadBooksFromDatabase(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<Book> books = objectMapper.readValue(
                    new File(filePath),
                    new TypeReference<List<Book>>() {}
            );

            for (Book book : books) {
                librarySystem.addBook(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
