package com.Trainingbackend.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Trainingbackend.entity.Upload;
import com.Trainingbackend.repository.UploadRepository;

@Service
public class UploadService {

	private final String uploadDir = "D:/student-uploads/";

	@Autowired
	private UploadRepository repo;

	public Upload uploadFile(Long studentId, MultipartFile file) throws IOException {

		File folder = new File(uploadDir);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

		File dest = new File(uploadDir + fileName);

		dest.getParentFile().mkdirs();

		file.transferTo(dest);

		String fileUrl = "http://localhost:8080/student-uploads/" + fileName;

		Upload upload = new Upload();
		upload.setStudentid(studentId);
		upload.setFileName(file.getOriginalFilename());
		upload.setFileType(file.getContentType());
		upload.setFileSize(file.getSize());
		upload.setFileUrl(fileUrl);

		return repo.save(upload);
	}

	public List<Upload> getStudentFiles(Long studentId) {
		return repo.findByStudentId(studentId);
	}

	public void deleteFile(Long id) {
		repo.deleteById(id);
	}
}