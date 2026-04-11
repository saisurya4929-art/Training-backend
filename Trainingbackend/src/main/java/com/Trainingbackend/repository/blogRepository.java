package com.Trainingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Trainingbackend.entity.Blog;

public interface blogRepository extends JpaRepository<Blog, Long>{
  
}
