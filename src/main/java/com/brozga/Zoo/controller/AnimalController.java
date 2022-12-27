package com.brozga.Zoo.controller;

import com.brozga.Zoo.exceptions.FoodExceededException;
import com.brozga.Zoo.model.*;
import com.brozga.Zoo.service.AnimalService;
import com.brozga.Zoo.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;
    private final ZoneService zoneService;

    @Autowired
    public AnimalController(AnimalService animalService, ZoneService zoneService) {
        this.animalService = animalService;
        this.zoneService = zoneService;
    }

    @PostMapping("/add/")
    public ResponseEntity<Animal> addAnimal(@RequestBody AnimalDto animalDto) {
        Zone z = zoneService.findZoneByName(animalDto.getZoneName());
        int count = zoneService.countFood(z);
        Animal animal = animalService.getAnimal(animalDto.getAnimalType());

        if (count + animal.getFood() <= z.getMaxFood()) {
            animal.setName(animalDto.getName());
            animal.setZone(z);
            return new ResponseEntity<>(animalService.addAnimal(animal), HttpStatus.CREATED);
        } else {
            throw new FoodExceededException("Food limit for Zone has been exceeded");
        }
    }


    @GetMapping("/{name}/")
    public List<Animal> showAllAnimalsWithName(@PathVariable String name) {
        return animalService.findAllByName(name);
    }

    @GetMapping("/zone/{zone}")
    public List<Animal> showAnimalsFromZone(@PathVariable String zone) {
        Zone z = zoneService.findZoneByName(zone);
        return animalService.findAllByZone(z.getId());
    }


}
