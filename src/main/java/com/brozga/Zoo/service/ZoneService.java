package com.brozga.Zoo.service;

import com.brozga.Zoo.exceptions.EntityNotFoundException;
import com.brozga.Zoo.model.Animal;
import com.brozga.Zoo.model.Zone;
import com.brozga.Zoo.repository.AnimalRepository;
import com.brozga.Zoo.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public ZoneService(ZoneRepository zoneRepository,
                       AnimalRepository animalRepository) {
        this.zoneRepository = zoneRepository;
        this.animalRepository = animalRepository;
    }

    public Zone addZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    public boolean checkIfZoneExist(String name) {
        return zoneRepository.existsByName(name);
    }

    public Zone findZoneByName(String name) {
        return zoneRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Zone has been not found"));
    }

    public Integer countFood(Zone zone) {
        List<Animal> animals = animalRepository.findAllByZoneId(zone.getId());
        return animals.stream().map(Animal -> Animal.getFood()).reduce(0, Integer::sum);
    }


    public String mostFood() {
        List<Zone> zones = zoneRepository.findAll();
        Map<Zone, Integer> zoneFood = new HashMap<>();
        Integer food;
        for (int i = 0; i < zones.size(); i++) {
            food = countFood(zones.get(i));
            zoneFood.put(zones.get(i), food);
        }

        LinkedHashMap<Zone, Integer> sortedMap = zoneFood
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Zone, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        System.out.println(sortedMap);
        return sortedMap.entrySet().iterator().next().getKey().getName();

    }

    public String leastAnimals() {
        List<Animal> animals = animalRepository.findAll();
        Map<String, Long> counts = animals
                .stream()
                .map(Animal -> Animal.getZone().getName())
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));


        LinkedHashMap<String, Long> sortedMap = counts
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        System.out.println();
        sortedMap.entrySet().forEach(System.out::println);

        return sortedMap.entrySet().iterator().next().getKey();
    }
}
