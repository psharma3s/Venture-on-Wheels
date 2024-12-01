package com.example.roadtripplanner.dao;

import com.example.roadtripplanner.model.User;

import java.util.List;

public interface UserDao {

    User getUserByUsername(String username);
    User getUserById(Integer userId);
    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Integer userId);
}
