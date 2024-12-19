package com.example.demo.model.usermanagement;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.model.Database;
import com.example.demo.model.exceptions.AccountNotFoundException;
import com.example.demo.model.exceptions.IncorrectPasswordException;
import com.example.demo.model.exceptions.PersonExistException;

/**
 * Handles authentication for users and admins in the system.
 * - Provides methods for logging in, registering, and exchanging tokens for
 * user information.
 * - Implements the `AuthenticationInterface`.
 * 
 * Key Methods:
 * - `loginUser`: Authenticates a user based on email and password, returning a
 * unique session token.
 * - `loginAdmin`: Authenticates an admin based on email, password, and admin
 * key, returning a unique session token.
 * - `registerUser`: Registers a new user, ensuring the email is not already in
 * use.
 * - `exchange`: Retrieves a `Person` (user or admin) based on a session token.
 * 
 * Utilises:
 * - `tokens`: A map of session tokens to user/admin identifiers.
 * - `Database`: For retrieving and storing user and admin data.
 * 
 * Exceptions:
 * - `AccountNotFoundException`: Thrown when a user or admin account is not
 * found.
 * - `IncorrectPasswordException`: Thrown when the provided password or admin
 * key is incorrect.
 * - `PersonExistException`: Thrown when trying to register a user with an
 * existing email.
 */

@Service
public class Authenticator implements AuthenticationInterface {
    private HashMap<String, String> tokens = new HashMap<>();

    @Override
    public String loginUser(User user) throws AccountNotFoundException, IncorrectPasswordException {
        User matchedUser = Database.findUser(user.getEmail());
        if (matchedUser == null) {
            throw new AccountNotFoundException();
        }

        if (!matchedUser.getPassword().equals(user.getPassword())) {
            throw new IncorrectPasswordException();
        }

        UUID uuid = UUID.randomUUID();
        tokens.put(uuid.toString(), matchedUser.getEmail());
        return uuid.toString();
    }

    @Override
    public String loginAdmin(Admin admin) throws AccountNotFoundException, IncorrectPasswordException {
        Admin matchedAdmin = Database.findAdmin(admin.getEmail(), admin.getPassword(), admin.getAdminKey());
        if (matchedAdmin == null) {
            // Check if the admin exists but the credentials are wrong
            if (Database.findAdmin(admin.getEmail(), null, null) != null) {
                throw new IncorrectPasswordException(); // Email exists, wrong password or key
            }
            throw new AccountNotFoundException(); // Email doesn't exist
        }

        UUID uuid = UUID.randomUUID();
        tokens.put(uuid.toString(), matchedAdmin.getId());
        return uuid.toString();
    }

    @Override
    public void registerUser(User user) throws PersonExistException {
        if (Database.findUser(user.getEmail()) != null) {
            throw new PersonExistException(); // User already exists
        }
        Database.addUser(user);
    }

    @Override
    public void registerAdmin(Admin admin) throws PersonExistException {
        throw new UnsupportedOperationException("Admins cannot be registered via the web interface.");
    }

    public Person exchange(String uuid) {
        Person user = Database.findUser(tokens.get(uuid));
        if (user == null) {
            user = Database.findAdminById(tokens.get(uuid));
        }
        return user;
    }

}
