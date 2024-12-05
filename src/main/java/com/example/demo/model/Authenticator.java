package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.demo.model.exceptions.PersonExistException;

@Service
public class Authenticator implements AuthenticationInterface {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Admin> admins = new ArrayList<>();

    HashMap<String, Person> tokens = new HashMap<>();

    @Override
    public String login(Person person) {
        for (User u : users) {
            if (u.getId().equals(person.getId())) {
                if (u.getPassword().equals(person.getPassword())) {
                    UUID uuid = UUID.randomUUID();
                    tokens.putIfAbsent(uuid.toString(), u);
                    return uuid.toString();
                }
            }
        }
        throw new RuntimeException("User not exist'");
    }

    @Override
    public void logout(String uuid) {
        tokens.remove(uuid);
    }

    @Override
    public void registerUser(User user) throws PersonExistException {
        for (User u : users) {
            if (u.getId().equals(user.getId())) {
                throw new PersonExistException();
            }
        }
        users.add(user);
    }

    @Override
    public void registerAdmin(Admin admin) throws PersonExistException {
        for (Admin a : admins) {
            if (a.getId().equals(admin.getId())) {
                throw new PersonExistException();
            }
        }
        admins.add(admin);
    }

    public Person exchange(String uuid) {
        return tokens.get(uuid);
    }
}
