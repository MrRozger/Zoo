package com.brozga.Zoo.repository;

import com.brozga.Zoo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZoneRepository extends JpaRepository<Zone, Long> {

    Optional<Zone> findByName(String name);
    boolean existsByName(String name);

}
