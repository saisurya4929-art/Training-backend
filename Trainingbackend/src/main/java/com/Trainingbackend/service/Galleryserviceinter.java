package com.Trainingbackend.service;

import java.util.List;

import com.Trainingbackend.entity.Gallery;

public interface Galleryserviceinter {
	
	Gallery saveGallery(Gallery gallery);
	
	List<Gallery>getAllGallery();

}
