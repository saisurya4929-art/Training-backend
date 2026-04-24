package com.Trainingbackend.service;

import java.util.List;

import com.Trainingbackend.entity.Placement;

public interface Placementserviceinter {
	
	Placement saveplacement(Placement placement);
	List<Placement>gettAllPalcements();
	
	Placement updatePlacement(Long id, Placement placement);
	void deletePlacement(Long id);

	void deleteBulkPlacement(List<Long> ids);
}
