package com.Trainingbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Trainingbackend.entity.Upload;

public interface UploadRepository extends JpaRepository<Upload, Long> {

	List<Upload> findByStudentId(Long studentId);

}
