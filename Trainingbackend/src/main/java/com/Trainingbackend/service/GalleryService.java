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

	@Override
	public Gallery updateGallery(Long id, Gallery gallery) {
		Gallery existingGallery = gallerepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Gallery not found"));

		existingGallery.setName(gallery.getName());
		existingGallery.setCategory(gallery.getCategory());

		if (gallery.getImagename() != null) {
			existingGallery.setImagename(gallery.getImagename());
		}

		if (gallery.getImageurl() != null) {
			existingGallery.setImageurl(gallery.getImageurl());
		}

		return gallerepo.save(existingGallery);
	
	
	}

	@Override
	public void deleteGallery(Long id) {
		
		gallerepo.deleteById(id);
		
	}

	@Override
	public void deleteBulkGallery(List<Long> ids) {
	  
		gallerepo.deleteAllById(ids);
		
	}

}
