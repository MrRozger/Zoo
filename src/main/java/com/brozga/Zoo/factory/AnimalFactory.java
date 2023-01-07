package com.brozga.Zoo.factory;


import com.brozga.Zoo.exceptions.UnrecognizedAnimalTypeException;
import com.brozga.Zoo.model.Animal;
import com.brozga.Zoo.model.Elephant;
import com.brozga.Zoo.model.Lion;
import com.brozga.Zoo.model.Rabbit;
import org.springframework.stereotype.Component;


@Component
public class AnimalFactory extends Factory{

    @Override
    public Animal addAnimal(String animalType) {

        switch(animalType.toUpperCase()){
            case "LION":
                return new Lion();
            case "ELEPHANT":
                return new Elephant();
            case "RABBIT":
                return new Rabbit();
            default:
                throw new  UnrecognizedAnimalTypeException("Animal Type not recognized");

        }
    }
}
