package com.example.marsroverkata.repository;

import com.example.marsroverkata.models.Obstacle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObstacleRepository extends JpaRepository<Obstacle, Long> {
    Boolean existsByXPositionAndYPosition(int xPosition, int yPosition);
}
