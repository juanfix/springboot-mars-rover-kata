package com.example.marsroverkata.infra.validations.obstacle;

import com.example.marsroverkata.dto.ObstacleDto;
import com.example.marsroverkata.repository.ObstacleRepository;
import com.example.marsroverkata.repository.RoverRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObstacleCanNotBeCreatedOnTheRoverOrAnotherObstacle implements ObstacleValidator {
    @Autowired
    private RoverRepository roverRepository;

    @Autowired
    private ObstacleRepository obstacleRepository;
    @Override
    public void validate(ObstacleDto obstacleDto) {
        if(obstacleRepository.existsByXPositionAndYPosition(obstacleDto.getXPosition(), obstacleDto.getYPosition())
        || roverRepository.existsByXPositionAndYPosition(obstacleDto.getXPosition(), obstacleDto.getYPosition())){
            throw new ValidationException("You can not create or update an obstacle on the Rover or another obstacle.");
        }
    }
}
