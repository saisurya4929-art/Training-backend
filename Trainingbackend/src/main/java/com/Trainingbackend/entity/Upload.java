package com.Trainingbackend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_doucment")
public class Upload {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;

	private long studentId;

	private String fileName;

	private String fileType;

	private String fileUrl;

	private Long fileSize;

	public Upload() {
	}

	public Upload(Long studentId, String fileName, String fileType, String fileUrl, Long fileSize) {
		this.studentId = studentId;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileUrl = fileUrl;
		this.fileSize = fileSize;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStudentid() {
		return studentId;
	}

	public void setStudentid(long studentid) {
		this.studentId = studentid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

}
