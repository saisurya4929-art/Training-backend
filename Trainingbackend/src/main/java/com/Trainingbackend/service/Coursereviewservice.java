package com.Trainingbackend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trainingbackend.entity.Coursereview;
import com.Trainingbackend.entity.Courses;
import com.Trainingbackend.entity.User;
import com.Trainingbackend.repository.CoursereviewRepository;
import com.Trainingbackend.repository.CoursesRepository;
import com.Trainingbackend.repository.UserRepository;

@Service
public class Coursereviewservice implements Coursereviewserviceinter {
	@Autowired
	private CoursereviewRepository reviewrepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CoursesRepository courseRepo;

	@Override
	public Coursereview addReview(Long studentId, Long courseId, int rating, String reviewText) {
		if (rating < 1 || rating > 5) {
			throw new RuntimeException("Rating must be between 1 and 5");
		}

		if (reviewrepo.existsByStudentIdAndCourseId(studentId, courseId)) {
			throw new RuntimeException("You already reviewed this course");
		}

		User student = userRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

		Courses course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

		Coursereview review = new Coursereview();
		review.setStudent(student);
		review.setCourse(course);
		review.setRating(rating);
		review.setReview(reviewText);
		review.setCreatedAt(LocalDateTime.now());

		return reviewrepo.save(review);
	}

	@Override
	public List<Coursereview> getCourseReviews(Long courseId) {

		return reviewrepo.findByCourseId(courseId);
	}

	@Override
	public List<Coursereview> getAllReviews() {

		return reviewrepo.findAll();
	}

}
