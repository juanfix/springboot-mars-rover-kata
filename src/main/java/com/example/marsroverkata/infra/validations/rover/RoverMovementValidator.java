package com.example.marsroverkata.infra.validations.rover;

import com.example.marsroverkata.dto.RoverDto;

public interface RoverMovementValidator {
    public boolean validate(Integer xPosition, Integer yPosition);
}
