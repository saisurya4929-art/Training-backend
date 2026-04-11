package com.Training.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Trainingbackend.entity.Gallery;
import com.Trainingbackend.service.Galleryserviceinter;

@RestController
@RequestMapping("/api/gallery")
@CrossOrigin(origins = "http://localhost:5173")
public class GalleryController {

    @Autowired
    private Galleryserviceinter galleryService;
    
    private final String uploadDir = "D:/uploads";

    @PostMapping("/add")
    public ResponseEntity<?> addGallery(
            @RequestParam("title") String title,
            @RequestParam("category") String category,
            @RequestParam("image") MultipartFile image) {

        try {
            System.out.println("title = " + title);
            System.out.println("category = " + category);
            System.out.println("image = " + image.getOriginalFilename());

            File folder = new File(uploadDir);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" +
                    StringUtils.cleanPath(image.getOriginalFilename());

            String filePath = uploadDir + File.separator + fileName;

            image.transferTo(new File(filePath));

            Gallery gallery = new Gallery();
            gallery.setName(title);
            gallery.setCategory(category);
            gallery.setImagename(fileName);
            gallery.setImageurl("http://localhost:8080/uploads/" + fileName);

            Gallery savedGallery = galleryService.saveGallery(gallery);

            return ResponseEntity.ok(savedGallery);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Gallery> getAllGallery() {
        return galleryService.getAllGallery();
    }

    
}