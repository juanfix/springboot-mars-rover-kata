package com.example.marsroverkata.repository;

import com.example.marsroverkata.models.Rover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoverRepository extends JpaRepository<Rover, Long> {
    Boolean existsByXPositionAndYPosition(int xPosition, int yPosition);
}
