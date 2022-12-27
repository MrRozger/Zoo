package com.brozga.Zoo.exceptions;

public class UnrecognizedAnimalTypeException extends RuntimeException {
    public UnrecognizedAnimalTypeException(String message) {
        super(message);
    }
}
