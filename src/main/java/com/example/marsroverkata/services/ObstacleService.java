package com.example.marsroverkata.services;

import com.example.marsroverkata.dto.ObstacleDto;
import com.example.marsroverkata.infra.validations.obstacle.ObstacleValidator;
import com.example.marsroverkata.models.Obstacle;
import com.example.marsroverkata.repository.ObstacleRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class ObstacleService implements IObstacleService {
    @Autowired
    private ObstacleRepository obstacleRepository;
    @Autowired
    private ObstacleValidator obstacleCanNotBeCreatedOnTheRoverOrAnotherObstacle;
    @Override
    public ResponseEntity<List<Obstacle>> findAll() {
        return ResponseEntity.ok(obstacleRepository.findAll());
    }

    @Override
    public ResponseEntity<Obstacle> createObstacle(ObstacleDto obstacleDto, UriComponentsBuilder uriComponentsBuilder) {
        obstacleCanNotBeCreatedOnTheRoverOrAnotherObstacle.validate(obstacleDto);

        Obstacle obstacle = obstacleRepository.save(new Obstacle(obstacleDto));

        URI url = uriComponentsBuilder.path("/api/obstacle/{id}").buildAndExpand(obstacle.getId()).toUri();
        return ResponseEntity.created(url).body(obstacle);
    }

    @Override
    public ResponseEntity<Obstacle> updateObstacle(Long id, ObstacleDto obstacleDto) {
        existsObstacleById(id);
        Obstacle obstacle = new Obstacle(obstacleDto);
        obstacle.setId(id);
        obstacleCanNotBeCreatedOnTheRoverOrAnotherObstacle.validate(obstacleDto);
        return ResponseEntity.ok(obstacleRepository.save(obstacle));
    }

    @Override
    public ResponseEntity deleteObstacle(Long id) {
        existsObstacleById(id);
        obstacleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private boolean existsObstacleById(Long id){
        if (obstacleRepository.existsById(id)){
            return true;
        } else {
            throw new ValidationException("Does not exists any obstacle with ID " + id);
        }
    }
}
