package com.example.demo.model;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.model.exceptions.AccountNotFoundException;
import com.example.demo.model.exceptions.IncorrectPasswordException;
import com.example.demo.model.exceptions.PersonExistException;

@Service
public class Authenticator implements AuthenticationInterface {
    HashMap<String, Person> tokens = new HashMap<>();

    @Override
    public String loginUser(User user) throws AccountNotFoundException, IncorrectPasswordException {
        User matchedUser = Database.findUser(user.getEmail(), user.getPassword());
        if (matchedUser == null) {
            if (Database.findUser(user.getEmail(), null) != null) {
                throw new IncorrectPasswordException(); // Email exists, wrong password
            }
            throw new AccountNotFoundException();
        }

        UUID uuid = UUID.randomUUID();
        tokens.put(uuid.toString(), matchedUser);
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
        tokens.put(uuid.toString(), matchedAdmin);
        return uuid.toString();
    }

    @Override
    public void logout(String uuid) {
        tokens.remove(uuid);
    }

    @Override
    public void registerUser(User user) throws PersonExistException {
        if (Database.findUser(user.getEmail(), null) != null) {
            throw new PersonExistException(); // User already exists
        }
        Database.addUser(user);
    }

    @Override
    public void registerAdmin(Admin admin) throws PersonExistException {
        throw new UnsupportedOperationException("Admins cannot be registered via the web interface.");
    }

    public Person exchange(String uuid) {
        return tokens.get(uuid);
    }

}
