package com.Trainingbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Trainingbackend.entity.Coursereview;

public interface CoursereviewRepository extends JpaRepository<Coursereview, Long> {
	List<Coursereview> findByCourseId(Long courseId);

	boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

}
