package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Database;
import com.example.demo.model.exceptions.AccountNotFoundException;
import com.example.demo.model.exceptions.IncorrectPasswordException;
import com.example.demo.model.exceptions.PersonExistException;
import com.example.demo.model.usermanagement.Authenticator;
import com.example.demo.model.usermanagement.Person;
import com.example.demo.model.usermanagement.User;

@ExtendWith(MockitoExtension.class)
public class AuthenticatorTest {
    Authenticator authenticator;

    @BeforeEach
    void init() {
        authenticator = new Authenticator();
    }

    @Test
    void testLoginUser_CheckTokenStored_Success() throws Exception {
        User inputUser = new User("test@example.com", "correctPassword");
        User foundUser = new User("test@example.com", "correctPassword");

        // Use mockStatic to intercept calls to Database.findUser()
        try (MockedStatic<Database> databaseMock = mockStatic(Database.class)) {
            databaseMock.when(() -> Database.findUser("test@example.com"))
                    .thenReturn(foundUser);

            String token = authenticator.loginUser(inputUser);

            assertNotNull(token, "Token should not be null when login succeeds");
            assertFalse(token.isEmpty(), "Token should not be empty when login succeeds");

            // Now let's verify that token was actually stored in Authenticator's map by
            // calling exchange()
            Person personFromToken = authenticator.exchange(token);
            assertNotNull(personFromToken, "Exchange should return a Person for a valid token");
            assertEquals("test@example.com", personFromToken.getEmail(),
                    "The Person from token should match the logged-in user");
        }
    }

    @Test
    void testLoginUser_AccountNotFound() {
        User inputUser = new User("test@example.com", "correctPassword");

        try (MockedStatic<Database> databaseMock = mockStatic(Database.class)) {
            // Database returns null to indicate user not found
            databaseMock.when(() -> Database.findUser("nonExistent@example.com"))
                    .thenReturn(null);

            assertThrows(AccountNotFoundException.class, () -> authenticator.loginUser(inputUser));
        }
    }

    @Test
    void testLoginUser_IncorrectPassword() {
        User inputUser = new User("test@example.com", "wrongPassword");
        User foundUser = new User("test@example.com", "correctPassword");

        try (MockedStatic<Database> databaseMock = mockStatic(Database.class)) {
            databaseMock.when(() -> Database.findUser("test@example.com"))
                    .thenReturn(foundUser);

            assertThrows(IncorrectPasswordException.class, () -> authenticator.loginUser(inputUser));
        }
    }

    @Test
    void testLoginUser_ExchangeReturnsNullForInvalidToken() throws Exception {
        User inputUser = new User("test@example.com", "correctPassword");
        User foundUser = new User("test@example.com", "correctPassword");

        try (MockedStatic<Database> databaseMock = mockStatic(Database.class)) {
            databaseMock.when(() -> Database.findUser("test@example.com"))
                    .thenReturn(foundUser);

            String token = authenticator.loginUser(inputUser);

            // Assert the real token works
            Person personFromRealToken = authenticator.exchange(token);
            assertNotNull(personFromRealToken, "exchange(token) should return the user for a valid token");

            // For an invalid token, exchange(...) should return null
            Person personFromFakeToken = authenticator.exchange("fake-token");
            assertNull(personFromFakeToken, "exchange(fake-token) should return null if token doesn't exist");
        }
    }

    @Test
    void testRegisterUser_Success() throws Exception { // Arrange
        User newUser = new User("newuser@example.com", "password123");

        // We want Database.findUser(...) to return null (user not found)
        try (MockedStatic<Database> dbMock = mockStatic(Database.class)) {
            dbMock.when(() -> Database.findUser("newuser@example.com"))
                    .thenReturn(null);

            // We also mock Database.addUser(...) so it doesn't do real I/O
            dbMock.when(() -> Database.addUser(newUser))
                    .thenReturn(true); // Suppose "true" means user is successfully added

            authenticator.registerUser(newUser);

            // Assert
            // 1. Verify no exception thrown
            // 2. Verify Database.findUser and Database.addUser were called with correct
            // parameters
            dbMock.verify(() -> Database.findUser("newuser@example.com"), times(1));
            dbMock.verify(() -> Database.addUser(newUser), times(1));
        }
    }

    @Test
    void testRegisterUser_UserAlreadyExists() {
        
        User existingUser = new User("existinguser@example.com", "password123");

        try (MockedStatic<Database> dbMock = mockStatic(Database.class)) {
            // Database.findUser(...) returns a non-null user (already exists)
            dbMock.when(() -> Database.findUser("existinguser@example.com"))
                    .thenReturn(existingUser);

            assertThrows(PersonExistException.class, () -> {
                authenticator.registerUser(existingUser);
            });

            dbMock.verify(() -> Database.findUser("existinguser@example.com"), times(1));
            // We do NOT expect Database.addUser to be called
            dbMock.verify(() -> Database.addUser(existingUser), never());
        }
    }

}
