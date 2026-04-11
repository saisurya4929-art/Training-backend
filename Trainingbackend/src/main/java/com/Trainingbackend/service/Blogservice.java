package com.Trainingbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trainingbackend.entity.Blog;
import com.Trainingbackend.repository.blogRepository;

@Service
public class Blogservice implements Blogserviceinter{
@Autowired
private blogRepository repo;

	@Override
	public Blog Saveblog(Blog blog) {
	
		return repo.save(blog);
	}

	@Override
	public List<Blog> getAllBlogs() {

		return repo.findAll();
	}

}
