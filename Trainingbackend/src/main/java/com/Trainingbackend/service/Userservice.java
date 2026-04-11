package com.Trainingbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trainingbackend.entity.User;
import com.Trainingbackend.repository.UserRepository;


@Service
public class Userservice implements Userserviceinter {
    @Autowired
    private UserRepository UserRepo;

	@Override
	public User login(String email, String password) {
	
		return UserRepo.findByEmailAndPassword(email, password);
	}
	@Override
	public User register(User user) {
		
		return UserRepo.save(user);
	}


		
		

}
