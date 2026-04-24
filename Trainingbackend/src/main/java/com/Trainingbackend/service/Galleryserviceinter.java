package com.Trainingbackend.service;

import java.util.List;

import com.Trainingbackend.entity.Gallery;

public interface Galleryserviceinter {
	
	Gallery saveGallery(Gallery gallery);
	
	List<Gallery>getAllGallery();
	
	Gallery updateGallery(Long id, Gallery gallery);
	void deleteGallery(Long id);

	void deleteBulkGallery(List<Long> ids);
}
