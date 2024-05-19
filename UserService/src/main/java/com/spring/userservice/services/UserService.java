package com.spring.userservice.services;

import com.spring.userservice.entities.User;

import java.util.List;

public interface UserService {
    //User operations

    //create
    User saveUser(User user);

    //get all user
    List<User> getUsers();

    //get single user

    User getUser(String userId);

    //delete user
    void deleteUser(String userId);
}
