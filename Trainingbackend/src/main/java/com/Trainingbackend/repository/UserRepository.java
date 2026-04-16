package com.Trainingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Trainingbackend.entity.User;

public interface UserRepository extends JpaRepository <User, Long>{
	
	  User findByEmail(String email);
	 
	  boolean existsByEmail(String email);

      User findByEmailAndPassword(String email, String password);
}
