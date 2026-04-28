package com.Trainingbackend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Trainingbackend.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    List<Enrollment> findByStudentId(Long studentId);
}