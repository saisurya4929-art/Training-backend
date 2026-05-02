package com.Training.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Trainingbackend.entity.Coursereview;
import com.Trainingbackend.service.Coursereviewservice;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:5173")

public class Coursereviewcontroller {
	@Autowired
	private Coursereviewservice reviewService;

	@PostMapping("/{studentId}/{courseId}")
	public Coursereview addReview(@PathVariable Long studentId, @PathVariable Long courseId,
			@RequestBody Map<String, String> body) {

		int rating = Integer.parseInt(body.get("rating"));
		String review = body.get("review");

		return reviewService.addReview(studentId, courseId, rating, review);
	}

	@GetMapping("/course/{courseId}")
	public List<Coursereview> getCourseReviews(@PathVariable Long courseId) {
		return reviewService.getCourseReviews(courseId);
	}

	@GetMapping("/all")
	public List<Coursereview> getAllReviews() {
		return reviewService.getAllReviews();
	}
}