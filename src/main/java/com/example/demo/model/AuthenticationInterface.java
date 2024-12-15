package com.example.demo.model;

import com.example.demo.model.exceptions.AccountNotFoundException;
import com.example.demo.model.exceptions.IncorrectPasswordException;
import com.example.demo.model.exceptions.PersonExistException;

public interface AuthenticationInterface {
    public String loginUser(User user) throws AccountNotFoundException, IncorrectPasswordException;

    public String loginAdmin(Admin admin) throws AccountNotFoundException, IncorrectPasswordException;

    public void registerUser(User user) throws PersonExistException;

    public void registerAdmin(Admin admin) throws PersonExistException;

}
