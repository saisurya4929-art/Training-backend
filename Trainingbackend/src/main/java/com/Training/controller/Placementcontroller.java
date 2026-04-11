

//

package com.Training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.Trainingbackend.entity.Placement;
import com.Trainingbackend.service.Placementservice;

@RestController
@RequestMapping("/api/placements")
@CrossOrigin(origins = "http://localhost:5173")
public class Placementcontroller {

    @Autowired
    private Placementservice placeserv;

    @PostMapping
    public Placement addplacement(@RequestBody Placement placement) {
        return placeserv.saveplacement(placement);
    }

    @GetMapping
    public List<Placement> getAllplacements() {
        return placeserv.gettAllPalcements();
    }
}