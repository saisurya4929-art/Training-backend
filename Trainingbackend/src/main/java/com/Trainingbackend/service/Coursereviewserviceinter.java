package com.Trainingbackend.service;

import java.util.List;

import com.Trainingbackend.entity.Coursereview;

public interface Coursereviewserviceinter {

	Coursereview addReview(Long studentId, Long courseId, int rating, String review);

	List<Coursereview> getCourseReviews(Long courseId);

	List<Coursereview> getAllReviews();

}
