package com.example.marsroverkata.infra.validations.rover;

import com.example.marsroverkata.dto.RoverDto;
import com.example.marsroverkata.repository.ObstacleRepository;
import com.example.marsroverkata.repository.RoverRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoverCanNotBeCreatedOrUpdatedOnTheObstacle implements RoverValidator {
    @Autowired
    private RoverRepository roverRepository;

    @Autowired
    private ObstacleRepository obstacleRepository;

    @Override
    public void validate(RoverDto roverDto) {
        if(obstacleRepository.existsByXPositionAndYPosition(roverDto.getXPosition(), roverDto.getYPosition())){
            throw new ValidationException("You can not create or update a Rover on the obstacle.");
        }

    }
}
