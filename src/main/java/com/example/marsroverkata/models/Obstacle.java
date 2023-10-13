package com.example.marsroverkata.models;

import com.example.marsroverkata.dto.ObstacleDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@AllArgsConstructor
@Table(name = "obstacles")
@EqualsAndHashCode(callSuper = true)
public class Obstacle extends Coordinate {
    public Obstacle(ObstacleDto obstacleDto) {
        super(null, obstacleDto.getXPosition(), obstacleDto.getYPosition());
    }
}
