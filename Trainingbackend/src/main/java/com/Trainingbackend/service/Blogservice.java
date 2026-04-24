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

	@Override
	public Blog updateBlog(Long id, Blog blog) {
		Blog existingBlog = repo.findById(id)
				.orElseThrow(() -> new RuntimeException("Blog not found"));

		existingBlog.setTitle(blog.getTitle());
		existingBlog.setCategory(blog.getCategory());
		existingBlog.setImage(blog.getImage());
		existingBlog.setDescription(blog.getDescription());

		return repo.save(existingBlog);
	
	}

	@Override
	public void deleteBlog(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	public void deleteBulkBlog(List<Long> ids) {
		repo.deleteAllById(ids);

		
	}

}
