package com.Trainingbackend.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Coursereview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int rating;

	@Column(columnDefinition = "TEXT")
	private String review;

	private LocalDateTime createdAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id")
	@JsonIgnoreProperties({ "password", "email", "role", "hibernateLazyInitializer", "handler" })
	private User student;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Courses course;

	public Coursereview() {
	}

	public Long getId() {
		return id;
	}

	public int getRating() {
		return rating;
	}

	public String getReview() {
		return review;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public User getStudent() {
		return student;
	}

	public Courses getCourse() {
		return course;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}
}