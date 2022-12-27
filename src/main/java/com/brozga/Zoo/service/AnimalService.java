package com.brozga.Zoo.service;

import com.brozga.Zoo.exceptions.UnrecognizedAnimalTypeException;
import com.brozga.Zoo.model.Animal;
import com.brozga.Zoo.model.Elephant;
import com.brozga.Zoo.model.Lion;
import com.brozga.Zoo.model.Rabbit;
import com.brozga.Zoo.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal addAnimal(Animal animal) {
        return (Animal) animalRepository.save(animal);
    }

    public List<Animal> findAllByName(String name) {
        return animalRepository.findByName(name);
    }

    public List<Animal> findAllByZone(Long id) {
        return animalRepository.findAllByZoneId(id);
    }

    public Animal getAnimal(String animalType) {
        switch (animalType.toUpperCase()) {
            case "ELEPHANT":
                return new Elephant();
            case "LION":
                return new Lion();
            case "RABBIT":
                return new Rabbit();
            default:
                throw new UnrecognizedAnimalTypeException("Given animal type is unrecognized");
        }
    }
}
