package com.example.demo.model;

import com.example.demo.model.exceptions.PersonExistException;

public interface AuthenticationInterface {
    public String login(Person person);

    public void logout(String uuid);

    public void registerUser(User user) throws PersonExistException;

    public void registerAdmin(Admin admin) throws PersonExistException;

}
