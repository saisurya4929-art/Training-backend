package com.Trainingbackend.service;

import java.util.List;
import com.Trainingbackend.entity.Enrollment;

public interface Enrollmentserviceinter {

    String enrollCourse(Long studentId, Long courseId);

    List<Enrollment> getStudentCourses(Long studentId);
    
    Enrollment updateProgress(Long enrollmentId, int completedLessons, int totalLessons);
}