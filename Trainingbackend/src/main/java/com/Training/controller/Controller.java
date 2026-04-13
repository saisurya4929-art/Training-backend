
package com.Training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Trainingbackend.entity.User;
import com.Trainingbackend.service.Userserviceinter;
import com.Trainingbackend.repository.UserRepository;

@CrossOrigin(origins ="http://localhost:5173")
@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private Userserviceinter service;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody User user){


        if(userRepository.existsByEmail(user.getEmail())){
            return "Email already exists";
        }

        String password = user.getPassword();

        boolean validPassword =
                password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[@$!%*?&].*");

        if(!validPassword){
            return "Password must be 8 characters and include uppercase, lowercase, number and special character";
        }

        if(user.getRole() == null || user.getRole().isEmpty()){
            user.setRole("STUDENT");
        }

        service.register(user);
        return "Registration successful";
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