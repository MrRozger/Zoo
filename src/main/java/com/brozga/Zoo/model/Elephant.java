package com.brozga.Zoo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@DiscriminatorValue("Elephant")
public class Elephant extends Animal {

    final int food = 20;

}
