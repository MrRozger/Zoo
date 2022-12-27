package com.brozga.Zoo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @Transient
    private final Integer maxFood = 100;

    @JsonBackReference
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Animal> animal;

}
