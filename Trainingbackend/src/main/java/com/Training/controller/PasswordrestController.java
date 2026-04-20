package com.Training.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Trainingbackend.service.Passwordrestserviceinter;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/password")
public class PasswordrestController{

    @Autowired
    private Passwordrestserviceinter passwordResetService;

    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");

         String token =  passwordResetService.cretaeResetToken(email);

        String resetLink = "http://localhost:5173/reset-password/" + token;

        Map<String, String> response = new HashMap<>();
        response.put("message", "Reset link generated successfully");
        response.put("resetLink", resetLink);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");


        passwordResetService.restPassword(token, newPassword);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Password reset successful");

        return ResponseEntity.ok(response);
    }
}