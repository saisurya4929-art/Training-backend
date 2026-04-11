package com.Trainingbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Trainingbackend.entity.User;



public interface UserRepository extends JpaRepository <User, Long>{
	


	User findByEmailAndPassword(String email, String password);
}
