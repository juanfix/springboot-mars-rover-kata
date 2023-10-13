package com.example.marsroverkata.infra.validations.rover;

import com.example.marsroverkata.models.Obstacle;
import com.example.marsroverkata.repository.ObstacleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CanRoverMoveToThisPosition implements RoverMovementValidator {
    @Autowired
    ObstacleRepository obstacleRepository;
    @Override
    public boolean validate(Integer xPosition, Integer yPosition) {
        List<Obstacle> obstacles = obstacleRepository.findAll();
        for (Obstacle obstacle:obstacles) {
            if (Objects.equals(obstacle.getXPosition(), xPosition)
                    && Objects.equals(obstacle.getYPosition(), yPosition)) {
                return false;
            }
        }
        return true;
    }
}
