package com.example.demo.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LibrarySystem {
    private AuthenticationInterface authenticator;
    private static Library library;

    public LibrarySystem(Authenticator authenticator) {
        this.authenticator = authenticator;

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            library = new Library(objectMapper.readValue(
                    new File("./src/main/resources/Books.json"),
                    new TypeReference<ArrayList<BookEntry>>() {
                    }));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Library getLibrary() {
        return library;
    }

    public AuthenticationInterface getAuth() {
        return authenticator;
    }
}