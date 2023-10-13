package com.example.marsroverkata.infra.validations.rover;

import com.example.marsroverkata.dto.RoverDto;
import com.example.marsroverkata.repository.RoverRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnlyOneRoverCreated implements RoverValidator {
    @Autowired
    private RoverRepository roverRepository;

    @Override
    public void validate(RoverDto roverDto) {
        if(!roverRepository.findAll().isEmpty())
            throw new ValidationException("You can only create one Rover. Use PUT method to change its parameters");
    }
}
