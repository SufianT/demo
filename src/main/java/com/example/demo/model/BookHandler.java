package com.example.demo.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class BookHandler {
    private  final LibrarySystem ls;

    public BookHandler(LibrarySystem ls) {
        this.ls = ls;
    }


    public void loadBooksFromDatabase(LibrarySystem librarySystem,String filePath) {
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
    public void saveBookToFile(Book book, String filePath) throws IOException {
        File file = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        List<Book> books;
        if (file.exists()) {
            books = mapper.readValue(file, new TypeReference<>() {});
        } else {
            books = new ArrayList<>();
        }
        boolean isbnExists = books.stream().anyMatch(existingBook -> existingBook.getISBN().equals(book.getISBN()));
        if (isbnExists) {
            throw new IllegalArgumentException("A book with this ISBN already exists: " + book.getISBN());
        }

        // Add the new book
        books.add(book);

        // Write the updated list back to the file
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, books);

        //add the new book to Booklist as a book object
        loadBooksFromDatabase(ls,filePath);

    }
}