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

	@Override
	public Courses updateCourse(Long id, Courses courses) {
		Courses existingCourse = coursesrepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Course not found"));

		existingCourse.setTitle(courses.getTitle());
		existingCourse.setDescription(courses.getDescription());
		existingCourse.setDuration(courses.getDuration());
		existingCourse.setImageUrl(courses.getImageUrl());

		return coursesrepo.save(existingCourse);
	}

	@Override
	public void deleteCourse(Long id) {
		
		coursesrepo.deleteById(id);
		
	}

	@Override
	public void deleteBulkCourses(List<Long> ids) {
		
		coursesrepo.deleteAllById(ids);
		
	}

}
