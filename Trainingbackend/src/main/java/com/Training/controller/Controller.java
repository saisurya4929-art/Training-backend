package com.Training.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Trainingbackend.entity.User;
import com.Trainingbackend.service.Userserviceinter;

@CrossOrigin(origins ="http://localhost:5173")
@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private Userserviceinter service;
    @PostMapping("/register")
    public User register(@RequestBody User user){
   
        return service.register(user);
    }

    
    @PostMapping("/login")
    public User login(@RequestBody User user) {
    	

        User existingUser = service.login(
                user.getEmail(),
                user.getPassword()
        );
       
        if(existingUser == null){
            throw new RuntimeException("Invalid Email or Password");
        }

        return existingUser;
    }
}


