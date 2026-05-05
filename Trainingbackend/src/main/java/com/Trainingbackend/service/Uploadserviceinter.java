package com.Trainingbackend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.Trainingbackend.entity.Upload;

public interface Uploadserviceinter {
	Upload uploadFile(Long studentId, MultipartFile file) throws IOException;

	List<Upload> getStudentFiles(Long studentId);

	void deleteFile(Long id);
}
