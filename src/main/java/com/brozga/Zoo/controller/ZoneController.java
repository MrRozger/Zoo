package com.brozga.Zoo.controller;

import com.brozga.Zoo.model.Zone;
import com.brozga.Zoo.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zone")
public class ZoneController {

    private final ZoneService zoneService;

    @Autowired
    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping("/add/")
    public ResponseEntity<String> addZone(@RequestBody Zone zone) {
        if (!zoneService.checkIfZoneExist(zone.getName())) {
            zoneService.addZone(zone);
            return new ResponseEntity<>("Zone Created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Given zone name is already created", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/foodReport")
    public ResponseEntity<String> createFoodReport() {
        String zone = zoneService.mostFood();
        return new ResponseEntity<>("Zone with the greatest need for food is: " + zone, HttpStatus.CREATED);
    }

    @GetMapping("/animalReport")
    public ResponseEntity<String> createAnimalReport() {
        String zone = zoneService.leastAnimals();
        return new ResponseEntity<>("Zone with the fewest animals is: " + zone, HttpStatus.CREATED);
    }
}