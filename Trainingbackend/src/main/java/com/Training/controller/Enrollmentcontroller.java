package com.Training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Trainingbackend.entity.Enrollment;
import com.Trainingbackend.service.Enrollmentserviceinter;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "http://localhost:5173")
public class Enrollmentcontroller {

	@Autowired
	private Enrollmentserviceinter service;

	@PostMapping("/{studentId}/{courseId}")
	public String enroll(@PathVariable Long studentId, @PathVariable Long courseId) {

		return service.enrollCourse(studentId, courseId);
	}

	@GetMapping("/student/{studentId}")
	public List<Enrollment> myCourses(@PathVariable Long studentId) {

		return service.getStudentCourses(studentId);
	}

	@PutMapping("/progress/{enrollmentId}")
	public Enrollment updateProgress(@PathVariable Long enrollmentId, @RequestParam int completedLessons,
			@RequestParam int totalLessons) {

		return service.updateProgress(enrollmentId, completedLessons, totalLessons);
	}
}