package com.brozga.Zoo.model;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("Lion")
public class Lion extends Animal{

    final int food = 11;
}
