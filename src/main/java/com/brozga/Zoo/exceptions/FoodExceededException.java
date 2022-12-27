package com.brozga.Zoo.exceptions;

public class FoodExceededException extends RuntimeException {
    public FoodExceededException(String message) {
        super(message);
    }
}
