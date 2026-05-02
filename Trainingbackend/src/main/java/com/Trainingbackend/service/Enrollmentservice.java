package com.Trainingbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trainingbackend.entity.Courses;
import com.Trainingbackend.entity.Enrollment;
import com.Trainingbackend.entity.User;
import com.Trainingbackend.repository.CoursesRepository;
import com.Trainingbackend.repository.EnrollmentRepository;
import com.Trainingbackend.repository.UserRepository;

@Service
public class Enrollmentservice implements Enrollmentserviceinter {

    @Autowired
    private EnrollmentRepository enrollmentRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CoursesRepository courseRepo;

    @Override
    public String enrollCourse(Long studentId, Long courseId) {

        if (enrollmentRepo.existsByStudentIdAndCourseId(studentId, courseId)) {
            return "Already Enrolled";
        }

        User student = userRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));

        Courses course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course Not Found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus("ENROLLED");
        enrollment.setEnrolledDate(java.time.LocalDate.now());

        enrollmentRepo.save(enrollment);

        return "Enrollment Successful";
    }

    @Override
    public List<Enrollment> getStudentCourses(Long studentId) {
        return enrollmentRepo.findByStudentId(studentId);
    }

	@Override
	public Enrollment updateProgress(Long enrollmentId, int completedLessons, int totalLessons) {
		Enrollment enrollment = enrollmentRepo.findById(enrollmentId)
	            .orElseThrow(() -> new RuntimeException("Enrollment Not Found"));

	    int progress = 0;

	    if (totalLessons > 0) {
	        progress = (completedLessons * 100) / totalLessons;
	    }

	    if (progress > 100) {
	        progress = 100;
	    }

	    enrollment.setCompletedLessons(completedLessons);
	    enrollment.setTotalLessons(totalLessons);
	    enrollment.setProgress(progress);

	    if (progress == 100) {
	        enrollment.setStatus("COMPLETED");
	    } else {
	        enrollment.setStatus("ENROLLED");
	    }

	    return enrollmentRepo.save(enrollment);
	}
		
	}
