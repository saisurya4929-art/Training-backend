package com.Trainingbackend.service;

import java.util.List;

import com.Trainingbackend.entity.User;

public interface Userserviceinter {

    User register(User user);

    User login(String email);
    
    List<User>getAllUsers();
    
    User updateUserRole(Long id, String role);

    void deleteUser(Long id);
}