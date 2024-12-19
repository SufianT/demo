package com.example.demo;

import com.example.demo.model.Database;
import com.example.demo.model.bookmanager.Book;
import com.example.demo.model.bookmanager.Genre;
import com.example.demo.model.searchengine.SearchEngine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the SearchEngine class using mocked Database calls.
 * Ensures no real JSON or file I/O is involved.
 */
@ExtendWith(MockitoExtension.class)
public class SearchEngineTest {

    private List<Book> mockBooks;

    @BeforeEach
    void setUp() {
        Book book1 = new Book(
                "Java Programming",
                "ABC123",
                List.of("John Doe"),
                "image1.jpg",
                Genre.Fantasy);

        Book book2 = new Book(
                "Advanced Java",
                "XYZ999",
                List.of("Jane Smith", "Bob Johnson"),
                "image2.jpg",
                Genre.Fiction);

        Book book3 = new Book(
                "Effective Testing",
                "112233",
                List.of("Test Author"),
                "image3.jpg",
                Genre.SCIFI);

        mockBooks = new ArrayList<>();
        mockBooks.add(book1);
        mockBooks.add(book2);
        mockBooks.add(book3);
    }

    @Test
    void testFind_SingleWord() {
        // Searching "Java" should find "Java Programming" (ABC123) and "Advanced Java"
        // (XYZ999)
        try (MockedStatic<Database> dbMock = mockStatic(Database.class)) {
            dbMock.when(Database::getBookList).thenReturn(mockBooks);

            SearchEngine engine = new SearchEngine();
            Set<String> result = engine.find("Java");

            assertNotNull(result);
            assertTrue(result.contains("ABC123"), "Should contain ABC123 for 'Java' keyword");
            assertTrue(result.contains("XYZ999"), "Should contain XYZ999 for 'Java' keyword");
            assertFalse(result.contains("112233"), "112233 does not include 'Java'");
        }
    }

    @Test
    void testFind_ExactMatch_ISBN() {
        // Searching by exact ISBN "ABC123"
        try (MockedStatic<Database> dbMock = mockStatic(Database.class)) {
            dbMock.when(Database::getBookList).thenReturn(mockBooks);

            SearchEngine engine = new SearchEngine();
            Set<String> result = engine.find("ABC123");

            assertNotNull(result);
            assertEquals(1, result.size(), "Should only match one book by exact ISBN");
            assertTrue(result.contains("ABC123"), "Should contain ABC123");
        }
    }

    @Test
    void testFind_ExactMatch_Title() {
        // Searching "Java Programming" results in "Java" and "Programming" keywords.
        // Both should lead us to ABC123.
        try (MockedStatic<Database> dbMock = mockStatic(Database.class)) {
            dbMock.when(Database::getBookList).thenReturn(mockBooks);

            SearchEngine engine = new SearchEngine();
            Set<String> result = engine.find("Java Programming");

            assertNotNull(result);
            assertTrue(result.contains("ABC123"), "Should contain ABC123 from 'Java Programming' search");
        }
    }

    @Test
    void testFind_AuthorName() {
        // Searching "Smith" should return the book authored by Jane Smith: XYZ999
        try (MockedStatic<Database> dbMock = mockStatic(Database.class)) {
            dbMock.when(Database::getBookList).thenReturn(mockBooks);

            SearchEngine engine = new SearchEngine();
            Set<String> result = engine.find("Smith");

            assertNotNull(result);
            assertTrue(result.contains("XYZ999"), "Should contain XYZ999 authored by Jane Smith");
        }
    }

    @Test
    void testFind_NoMatch() {
        try (MockedStatic<Database> dbMock = mockStatic(Database.class)) {
            dbMock.when(Database::getBookList).thenReturn(mockBooks);

            SearchEngine engine = new SearchEngine();
            Set<String> result = engine.find("NonExistentTerm");

            assertNotNull(result);
            assertTrue(result.isEmpty(), "No books should match 'NonExistentTerm'");
        }
    }
}
