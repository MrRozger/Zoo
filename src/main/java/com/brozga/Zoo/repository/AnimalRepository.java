package com.brozga.Zoo.repository;

import com.brozga.Zoo.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository<T extends Animal> extends JpaRepository<T, Long> {

    List<Animal> findByName(String name);

    List<Animal> findAllByZoneId(Long id);

}
