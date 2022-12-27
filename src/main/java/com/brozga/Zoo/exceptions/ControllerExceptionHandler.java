package com.brozga.Zoo.exceptions;

import com.brozga.Zoo.repository.ZoneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {
    private final ZoneRepository zoneRepository;

    public ControllerExceptionHandler(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @ExceptionHandler(FoodExceededException.class)
    public ResponseEntity<Object> handleExceededFoodLimitException(FoodExceededException e) {

        HttpStatus unprocessableEntity = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomException customException = new CustomException(
                e.getMessage(),
                unprocessableEntity,
                ZonedDateTime.now(ZoneId.of("UTC"))
        );
        return new ResponseEntity<>(customException, unprocessableEntity);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e){

        HttpStatus entityNotFound = HttpStatus.NOT_FOUND;
        CustomException customException = new CustomException(
                e.getMessage(),
                entityNotFound,
                ZonedDateTime.now(ZoneId.of("UTC"))
        );
        return new ResponseEntity<>(customException,entityNotFound);
    }
    @ExceptionHandler(UnrecognizedAnimalTypeException.class)
    public ResponseEntity<Object> handleUnrecognizedAnimalType(UnrecognizedAnimalTypeException e){

        HttpStatus unrecognizedAnimalType = HttpStatus.NOT_ACCEPTABLE;
        CustomException customException = new CustomException(
                e.getMessage(),
                unrecognizedAnimalType,
                ZonedDateTime.now(ZoneId.of("UTC"))
        );
        return new ResponseEntity<>(customException,unrecognizedAnimalType);
    }


}
