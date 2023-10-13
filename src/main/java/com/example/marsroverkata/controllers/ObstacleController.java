package com.example.marsroverkata.controllers;

import com.example.marsroverkata.dto.ObstacleDto;
import com.example.marsroverkata.models.Obstacle;
import com.example.marsroverkata.services.IObstacleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/obstacle")
public class ObstacleController {
    @Autowired
    private IObstacleService obstacleService;

    @GetMapping
    public ResponseEntity<List<Obstacle>> findAll() {
        return obstacleService.findAll();
    }

    @PostMapping
    public ResponseEntity<Obstacle> createObstacle(@RequestBody ObstacleDto obstacleDto, UriComponentsBuilder uriComponentsBuilder) {
        return obstacleService.createObstacle(obstacleDto, uriComponentsBuilder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obstacle> updateObstacle(@PathVariable Long id, @RequestBody ObstacleDto obstacleDto) {
        return obstacleService.updateObstacle(id, obstacleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteObstacle(@PathVariable Long id) {
        return obstacleService.deleteObstacle(id);
    }
}
