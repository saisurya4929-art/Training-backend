package com.Trainingbackend.entity;

import java.time.LocalDate;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate enrolledDate;

    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @JsonIgnoreProperties({"password","email","role"})
    private User student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Courses course;
    
    public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getCompletedLessons() {
		return completedLessons;
	}

	public void setCompletedLessons(int completedLessons) {
		this.completedLessons = completedLessons;
	}

	public int getTotalLessons() {
		return totalLessons;
	}

	public void setTotalLessons(int totalLessons) {
		this.totalLessons = totalLessons;
	}

	private int progress;

    private int completedLessons;

    private int totalLessons;

    public Enrollment() {
    }

    public Enrollment(User student, Courses course) {
        this.student = student;
        this.course = course;
        this.enrolledDate = LocalDate.now();
        this.status = "ENROLLED";
    }

    public Long getId() {
        return id;
    }

    public LocalDate getEnrolledDate() {
        return enrolledDate;
    }

    public String getStatus() {
        return status;
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

    public void setEnrolledDate(LocalDate enrolledDate) {
        this.enrolledDate = enrolledDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }
}