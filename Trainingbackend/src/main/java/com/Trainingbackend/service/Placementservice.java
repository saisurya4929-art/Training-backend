package com.Trainingbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trainingbackend.entity.Placement;
import com.Trainingbackend.repository.PlacementRepository;

@Service
public class Placementservice implements Placementserviceinter{
	@Autowired
	private PlacementRepository placementRepo;

	@Override
	public Placement saveplacement(Placement placement) {
	
		return placementRepo.save(placement );
	}

	@Override
	public List<Placement> gettAllPalcements() {
	
		return placementRepo.findAll();
	}

	@Override
	public Placement updatePlacement(Long id, Placement placement) {
		Placement existingPlacement = placementRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Placement not found"));

		existingPlacement.setStudentName(placement.getStudentName());
		existingPlacement.setCompany(placement.getCompany());
		existingPlacement.setRole(placement.getRole());
		existingPlacement.setPackageAmount(placement.getPackageAmount());
		existingPlacement.setImageUrl(placement.getImageUrl());

		return placementRepo.save(existingPlacement);
	
	}

	@Override
	public void deletePlacement(Long id) {
		placementRepo.deleteById(id);
		
		
	}

	@Override
	public void deleteBulkPlacement(List<Long> ids) {
		placementRepo.deleteAllById(ids);
		
	}

	

}
