package com.Trainingbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trainingbackend.entity.Gallery;
import com.Trainingbackend.repository.GalleryRepository;

@Service
public class GalleryService implements Galleryserviceinter {
@Autowired
private GalleryRepository gallerepo;
	@Override
	public Gallery saveGallery(Gallery gallery) {
	
		return gallerepo.save(gallery);
	}

	@Override
	public List<Gallery> getAllGallery() {
	
		return gallerepo.findAll();
	}

}
