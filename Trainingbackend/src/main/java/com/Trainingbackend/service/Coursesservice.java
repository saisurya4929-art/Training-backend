package com.Trainingbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trainingbackend.entity.Courses;
import com.Trainingbackend.repository.CoursesRepository;

@Service
public class Coursesservice implements Coursesserviceinter {
@Autowired
    private CoursesRepository coursesrepo;
	@Override
	public Courses addcourses(Courses courses) {
		return coursesrepo.save(courses);
	}

	@Override
	public List<Courses> getAllCourses() {
		return coursesrepo.findAll();
	}

}
