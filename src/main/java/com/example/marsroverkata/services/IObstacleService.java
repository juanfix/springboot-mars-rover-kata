package com.example.marsroverkata.services;

import com.example.marsroverkata.dto.ObstacleDto;
import com.example.marsroverkata.models.Obstacle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface IObstacleService {
    public ResponseEntity<List<Obstacle>> findAll();
    public ResponseEntity<Obstacle> createObstacle(ObstacleDto obstacleDto, UriComponentsBuilder uriComponentsBuilder);
    public ResponseEntity<Obstacle> updateObstacle(Long id, ObstacleDto obstacleDto);
    public ResponseEntity deleteObstacle(Long id);
}
