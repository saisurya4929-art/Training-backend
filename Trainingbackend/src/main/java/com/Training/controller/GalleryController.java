package com.Training.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            if (image == null || image.isEmpty()) {
                return ResponseEntity.badRequest().body("Please select an image");
            }

            File folder = new File(uploadDir);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String originalFileName = StringUtils.cleanPath(image.getOriginalFilename());
            String fileName = UUID.randomUUID().toString() + "_" + originalFileName;

            File destinationFile = new File(uploadDir + File.separator + fileName);
            image.transferTo(destinationFile);

            Gallery gallery = new Gallery();
            gallery.setName(title);
            gallery.setCategory(category);
            gallery.setImagename(originalFileName);
            gallery.setImageurl(fileName);

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
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGallery(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("category") String category,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            Gallery gallery = new Gallery();
            gallery.setName(title);
            gallery.setCategory(category);

            if (image != null && !image.isEmpty()) {
                File folder = new File(uploadDir);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                String originalFileName = StringUtils.cleanPath(image.getOriginalFilename());
                String fileName = UUID.randomUUID().toString() + "_" + originalFileName;

                File destinationFile = new File(uploadDir + File.separator + fileName);
                image.transferTo(destinationFile);

                gallery.setImagename(originalFileName);
                gallery.setImageurl(fileName);
            }

            Gallery updatedGallery = galleryService.updateGallery(id, gallery);
            return ResponseEntity.ok(updatedGallery);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Update failed: " + e.getMessage());
        }
    }
    @DeleteMapping("/bulk-delete")
    public String bulkDeleteGallery(@RequestBody List<Long> ids) {
        galleryService.deleteBulkGallery(ids);
        return "Selected gallery images deleted successfully";
    }


    @DeleteMapping("/{id}")
    public String deleteGallery(@PathVariable Long id) {
        galleryService.deleteGallery(id);
        return "Gallery deleted successfully";
    }
}