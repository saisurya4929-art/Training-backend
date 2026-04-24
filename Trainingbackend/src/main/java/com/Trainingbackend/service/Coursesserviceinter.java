package com.Trainingbackend.service;

import java.util.List;

import com.Trainingbackend.entity.Courses;

public interface Coursesserviceinter {
	
	Courses addcourses(Courses courses);
	List<Courses>getAllCourses();

	Courses updateCourse(Long id, Courses courses);
	void deleteCourse(Long id);
	
	void deleteBulkCourses(List<Long> ids);
}
