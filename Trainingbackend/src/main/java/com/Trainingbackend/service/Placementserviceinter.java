package com.Trainingbackend.service;

import java.util.List;

import com.Trainingbackend.entity.Placement;

public interface Placementserviceinter {
	
	Placement saveplacement(Placement placement);
	List<Placement>gettAllPalcements();


}
