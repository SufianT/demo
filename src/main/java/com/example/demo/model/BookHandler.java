package com.example.demo.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BookHandler {
    private final Library library;

    public BookHandler(Library library) {
        this.library = library;
    }

    public void loadBooksFromDatabase(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<Book> books = objectMapper.readValue(
                    new File(filePath),
                    new TypeReference<List<Book>>() {}
            );

            for (Book book : books) {
                library.addBook(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
