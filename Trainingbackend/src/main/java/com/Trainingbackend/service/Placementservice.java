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

	

}
