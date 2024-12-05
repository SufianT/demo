package com.example.demo.model;

public interface AuthenticationInterface {

    public default void registerAdmin(Admin admin){

    }
    public default void registerUser(User user){

    }
    default void logIn(Person person){}
    default void logOut(Person person){}
}
