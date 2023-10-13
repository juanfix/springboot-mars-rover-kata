package com.example.marsroverkata.services;

import com.example.marsroverkata.dto.CommandDto;
import com.example.marsroverkata.dto.RoverDto;
import com.example.marsroverkata.models.Rover;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface IRoverService {
    public ResponseEntity<Rover> getRover();
    public ResponseEntity<Rover> createRover(RoverDto rover, UriComponentsBuilder uriComponentsBuilder);
    public ResponseEntity<Rover> updateRover(Long id, RoverDto roverDto);
    public ResponseEntity deleteRover(Long id);
    public ResponseEntity<Rover> sendCommand(CommandDto commands);
}
