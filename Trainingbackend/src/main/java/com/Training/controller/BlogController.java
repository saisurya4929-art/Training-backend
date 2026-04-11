package com.Training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Trainingbackend.entity.Blog;
import com.Trainingbackend.service.Blogservice;

@RestController
@RequestMapping("api/blogs")
@CrossOrigin(origins ="http://localhost:5173")

public class BlogController {
	
@Autowired
private Blogservice service;

@PostMapping
	public Blog addBlog(@RequestBody Blog blog) {
	  return service.Saveblog(blog);
}
@GetMapping
public List<Blog> getBlogs() {
    return service.getAllBlogs();
}
	

}

