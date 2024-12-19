package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import com.example.demo.model.Database;
import com.example.demo.model.LibrarySystem;
import com.example.demo.model.bookmanager.Book;
import com.example.demo.model.bookmanager.Genre;
import com.example.demo.model.usermanagement.Authenticator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class LibrarySystemTest {

    @Test
    void testAddBook_CallsDatabase() {
        // Mock the Database static class
        try (MockedStatic<Database> dbMock = mockStatic(Database.class)) {
            // Create a mock LibrarySystem
            LibrarySystem librarySystem = new LibrarySystem(mock(Authenticator.class));

            Book newBook = new Book("Test Book", "12345", List.of("Author Name"), "image.jpg", Genre.Fiction);

            librarySystem.addBook(newBook);

            // Assert: Verify that Database.addBook was called
            dbMock.verify(() -> Database.addBook(newBook), times(1));
        }
    }

    @Test
    void testGetBookList_CallsDatabase() {
        try (MockedStatic<Database> dbMock = mockStatic(Database.class)) {
            // Mock the return value of Database.getBookList
            ArrayList<Book> mockBooks = new ArrayList<>();

            Book a = new Book("Mock Book 1", "12345", List.of("Author A"), "image1.jpg", Genre.Fantasy);
            Book b = new Book("Mock Book 2", "67890", List.of("Author B"), "image2.jpg", Genre.Romance);

            mockBooks.add(a);
            mockBooks.add(b);

            dbMock.when(Database::getBookList).thenReturn(mockBooks);

            // Create the LibrarySystem
            LibrarySystem librarySystem = new LibrarySystem(mock(Authenticator.class));

            List<Book> result = librarySystem.getBookList();

            dbMock.verify(Database::getBookList, times(1)); // Ensure the method is called
            assertEquals(mockBooks, result, "Returned book list should match the mocked database response");
        }
    }
}
