package com.Training.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Trainingbackend.entity.Upload;
import com.Trainingbackend.service.UploadService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/upload")
public class UploadController {

	@Autowired
	private UploadService uploadserv;

	// ✅ FIXED
	@PostMapping("/{studentId}")
	public Upload uploadFile(@PathVariable Long studentId, @RequestParam("file") MultipartFile file)
			throws IOException {

		System.out.println("UPLOAD API HIT");

		return uploadserv.uploadFile(studentId, file);
	}

	@GetMapping("/{studentId}")
	public List<Upload> getStudentFiles(@PathVariable Long studentId) {
		return uploadserv.getStudentFiles(studentId);
	}

	@DeleteMapping("/{id}")
	public String deleteFile(@PathVariable Long id) {
		uploadserv.deleteFile(id);
		return "File deleted successfully";
	}
}