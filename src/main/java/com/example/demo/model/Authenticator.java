package com.example.demo.model;

import java.util.ArrayList;

public class Authenticator implements AuthenticationInterface{
    ArrayList<User> userList= new ArrayList<>();
    ArrayList<Admin> adminList= new ArrayList<>();
    @Override
    public void registerAdmin(Admin admin){
        adminList.add(admin);
    }
    @Override

    public void registerUser(User user){
        userList.add(user);
    }
}
