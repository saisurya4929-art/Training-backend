package com.Trainingbackend.service;

import com.Trainingbackend.entity.Blog;
import java.util.List;
public interface Blogserviceinter {
	
	Blog Saveblog(Blog blog);
	
	List<Blog>getAllBlogs();
	
	Blog updateBlog(Long id, Blog blog);
	void deleteBlog(Long id);
	
	void deleteBulkBlog(List<Long> ids);

}
