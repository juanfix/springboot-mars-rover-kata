package com.example.marsroverkata.dto;

import com.example.marsroverkata.enums.Direction;
import com.example.marsroverkata.models.Coordinate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoverDto extends Coordinate {
    private Direction direction;

    public RoverDto(int xPosition, int yPosition, Direction direction) {
        super(null, xPosition, yPosition);
        this.direction = direction;
    }
}
