package com.Training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Trainingbackend.entity.Courses;
import com.Trainingbackend.service.Coursesservice;

@RestController
@RequestMapping("api/courses")
@CrossOrigin(origins = "http://localhost:5173")
public class CoursesController {
	@Autowired
	private Coursesservice courseser;

	@PostMapping
	public Courses addCourses(@RequestBody Courses courses) {
		return courseser.addcourses(courses);
	}

	@GetMapping
	public List<Courses> getAllCourses() {
		return courseser.getAllCourses();
	}

	@PutMapping("/{id}")
	public Courses updateCourse(@PathVariable Long id, @RequestBody Courses courses) {
		return courseser.updateCourse(id, courses);
	}

	@DeleteMapping("/{id}")
	public String deleteCourse(@PathVariable Long id) {
		courseser.deleteCourse(id);
		return "Course deleted successfully";
	}

	@DeleteMapping("/bulk-delete")
	public String bulkDeleteCourses(@RequestBody List<Long> ids) {
		courseser.deleteBulkCourses(ids);
		return "Selected courses deleted successfully";
	}

}
