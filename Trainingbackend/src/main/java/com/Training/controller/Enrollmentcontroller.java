package com.Training.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Trainingbackend.entity.Enrollment;
import com.Trainingbackend.service.Enrollmentserviceinter;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "http://localhost:5173")
public class Enrollmentcontroller {

    @Autowired
    private Enrollmentserviceinter service;

    @PostMapping("/{studentId}/{courseId}")
    public String enroll(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        return service.enrollCourse(studentId, courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> myCourses(
            @PathVariable Long studentId) {

        return service.getStudentCourses(studentId);
    }
}