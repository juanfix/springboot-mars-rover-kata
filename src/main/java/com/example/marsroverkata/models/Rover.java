package com.example.marsroverkata.models;

import com.example.marsroverkata.dto.RoverDto;
import com.example.marsroverkata.enums.Direction;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rovers")
@EqualsAndHashCode(callSuper = true)
public class Rover extends Coordinate {

    @Enumerated(EnumType.STRING)
    @Column(name = "direction")
    private Direction direction;

    public Rover(RoverDto roverDto) {
        super(null, roverDto.getXPosition(), roverDto.getYPosition());
        this.direction = roverDto.getDirection();
    }
}
