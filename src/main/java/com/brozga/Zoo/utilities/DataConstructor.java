package com.brozga.Zoo.utilities;

import com.brozga.Zoo.model.*;
import com.brozga.Zoo.repository.ZoneRepository;
import com.brozga.Zoo.service.AnimalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataConstructor {

    private final ZoneRepository zoneRepository;

    private final AnimalService animalService;

    @Autowired
    public DataConstructor(ZoneRepository zoneRepository, AnimalService animalService) {
        this.zoneRepository = zoneRepository;
        this.animalService = animalService;
    }

    @PostConstruct
    public void dataConstruct() {
        Zone zone = new Zone();
        zone.setName("First");
        zoneRepository.save(zone);
        Elephant elephant = new Elephant();
        Lion lion = new Lion();
        Rabbit rabbit = new Rabbit();
        elephant.setName("Ele");
        lion.setName("Li");
        rabbit.setName("Ra");
        elephant.setZone(zone);
        lion.setZone(zone);
        rabbit.setZone(zone);
        animalService.addAnimal(rabbit);
        animalService.addAnimal(elephant);
        animalService.addAnimal(lion);

        Zone zone1 = new Zone();
        zone1.setName("Second");
        zoneRepository.save(zone1);
        Elephant elephant1 = new Elephant();
        Elephant elephant2 = new Elephant();
        Elephant elephant3 = new Elephant();
        elephant1.setName("Lauron");
        elephant2.setName("Oragon");
        elephant3.setName("Bazylion");
        elephant1.setZone(zone1);
        elephant2.setZone(zone1);
        elephant3.setZone(zone1);
        animalService.addAnimal(elephant1);
        animalService.addAnimal(elephant2);
        //animalService.addAnimal(elephant3);


        Zone zone2 = new Zone();
        zone2.setName("Third");
        zoneRepository.save(zone2);
        Elephant elephant4 = new Elephant();
        Rabbit rabbit1 = new Rabbit();
        elephant4.setName("Szafran");
        elephant4.setZone(zone2);
        rabbit1.setName("Kuminian");
        rabbit1.setZone(zone2);
        //animalService.addAnimal(elephant4);
        animalService.addAnimal(rabbit1);

    }
}
