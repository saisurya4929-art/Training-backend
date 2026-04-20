package com.Training.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.Trainingbackend.entity.User;
import com.Trainingbackend.repository.UserRepository;
import com.Trainingbackend.security.JwtUtil;
import com.Trainingbackend.service.Userserviceinter;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private Userserviceinter service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email already exists");
        }

        String password = user.getPassword();

        boolean validPassword =
                password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[@$!%*?&].*");

        if (!validPassword) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Password must be 8 characters and include uppercase, lowercase, number and special character");
        }

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("STUDENT");
        }

        User savedUser = service.register(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User existingUser = service.login(user.getEmail());

        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Email");
        }

        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Password");
        }

        String token = jwtUtil.generateToken(existingUser);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("id", existingUser.getId());
        response.put("name", existingUser.getName());
        response.put("email", existingUser.getEmail());
        response.put("role", existingUser.getRole());
        response.put("courses", existingUser.getCourses());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/profile")
    public ResponseEntity<?> studentProfile() {
        return ResponseEntity.ok("Student profile protected API");
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<?> adminDashboard() {
        return ResponseEntity.ok("Admin dashboard protected API");
    }
}