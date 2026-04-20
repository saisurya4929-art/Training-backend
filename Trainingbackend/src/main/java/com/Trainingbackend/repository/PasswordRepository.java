package com.Trainingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Trainingbackend.entity.Passwordrest;


public interface PasswordRepository extends JpaRepository<Passwordrest, Long>{
	
	 Passwordrest findByToken(String Token);

}
