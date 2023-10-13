package com.example.marsroverkata.services;

import com.example.marsroverkata.dto.CommandDto;
import com.example.marsroverkata.dto.RoverDto;
import com.example.marsroverkata.enums.Direction;
import com.example.marsroverkata.infra.validations.rover.RoverMovementValidator;
import com.example.marsroverkata.infra.validations.rover.RoverValidator;
import com.example.marsroverkata.models.Obstacle;
import com.example.marsroverkata.models.Rover;
import com.example.marsroverkata.repository.ObstacleRepository;
import com.example.marsroverkata.repository.RoverRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class RoverService implements IRoverService{

    @Autowired
    RoverRepository roverRepository;

    @Autowired
    ObstacleRepository obstacleRepository;

    @Autowired
    private List<RoverValidator> validatorList;

    @Autowired
    private RoverValidator roverCanNotBeCreatedOrUpdatedOnTheObstacle;

    @Autowired
    private RoverMovementValidator canRoverMoveToThisPosition;

    @Override
    public ResponseEntity<Rover> getRover() {
        try {
            return ResponseEntity.ok(roverRepository.findAll().get(0));
        } catch (IndexOutOfBoundsException e){
            throw new ValidationException("There is not a Rover created. Use POST method.");
        }
    }

    @Override
    public ResponseEntity<Rover> createRover(RoverDto roverDto, UriComponentsBuilder uriComponentsBuilder) {

        validatorList.forEach(v-> v.validate(roverDto));

        Rover rover = roverRepository.save(new Rover(roverDto));

        URI url = uriComponentsBuilder.path("/api/rover/{id}").buildAndExpand(rover.getId()).toUri();
        return ResponseEntity.created(url).body(rover);

    }

    @Override
    public ResponseEntity<Rover> updateRover(Long id, RoverDto roverDto) {
        existsRoverById(id);
        Rover rover = new Rover(roverDto);
        rover.setId(id);
        roverCanNotBeCreatedOrUpdatedOnTheObstacle.validate(roverDto);
        return ResponseEntity.ok(roverRepository.save(rover));
    }

    @Override
    public ResponseEntity deleteRover(Long id) {
        existsRoverById(id);
        roverRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Rover> sendCommand(CommandDto commands) {
        Rover rover = this.getRover().getBody();

        for (String command:commands.getCommands()) {
            switch(command) {
                case "F": moveRover(rover, true); break;
                case "B": moveRover(rover, false); break;
                case "R": turnRover(rover, true); break;
                case "L": turnRover(rover, false); break;
            }
            roverRepository.save(rover);
        }

        return ResponseEntity.ok(rover);
    }

    // Functions

    private boolean existsRoverById(Long id){
        if (roverRepository.existsById(id)){
            return true;
        } else {
            throw new ValidationException("Does not exists any Rover with ID " + id);
        }
    }

    private void moveRover(Rover rover, boolean isForward) {
        int posXFinal = rover.getXPosition();
        int posYFinal = rover.getYPosition();

        Direction direction = rover.getDirection();
        if (Direction.EAST.equals(direction)
                || Direction.WEST.equals(direction)) {
            posXFinal -= isForward ? direction.getValue() : -direction.getValue();
        }

        if (Direction.NORTH.equals(direction)
                || Direction.SOUTH.equals(direction)) {
            posYFinal -= isForward ? direction.getValue() : -direction.getValue();
        }

        if (canRoverMoveToThisPosition.validate(posXFinal, posYFinal)) {
            rover.setXPosition(posXFinal);
            rover.setYPosition(posYFinal);
        }
    }

    private void turnRover(Rover rover, boolean isRight) {
        Direction direction = rover.getDirection();
        Direction finalDirection = null;

        if (isRight) {
            switch(direction) {
                case NORTH: finalDirection = Direction.EAST; break;
                case EAST: finalDirection = Direction.SOUTH; break;
                case SOUTH: finalDirection = Direction.WEST; break;
                case WEST: finalDirection = Direction.NORTH; break;
            }
        } else {
            switch(direction) {
                case NORTH: finalDirection = Direction.WEST; break;
                case WEST: finalDirection = Direction.SOUTH; break;
                case SOUTH: finalDirection = Direction.EAST; break;
                case EAST: finalDirection = Direction.NORTH; break;
            }
        }
        rover.setDirection(finalDirection);
    }

}
