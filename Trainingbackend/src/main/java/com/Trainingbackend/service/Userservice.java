package com.Trainingbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Trainingbackend.entity.User;
import com.Trainingbackend.repository.UserRepository;

@Service
public class Userservice implements Userserviceinter {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User login(String email) {
        return userRepo.findByEmail(email);
    }
    @Override
	public List<User> getAllUsers() {
		
		return userRepo.findAll();
	}

	@Override
	public User updateUserRole(Long id, String role) {
		User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(role);
        return userRepo.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
		
	}

	
}