package com.Trainingbackend.service;

import com.Trainingbackend.entity.Blog;
import java.util.List;
public interface Blogserviceinter {
	
	Blog Saveblog(Blog blog);
	
	List<Blog>getAllBlogs();

}
