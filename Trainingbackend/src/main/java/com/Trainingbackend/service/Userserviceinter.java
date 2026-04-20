package com.Trainingbackend.service;

import com.Trainingbackend.entity.User;

public interface Userserviceinter {

    User register(User user);

    User login(String email);
}