package com.example.marsroverkata.controllers;

import com.example.marsroverkata.dto.CommandDto;
import com.example.marsroverkata.dto.RoverDto;
import com.example.marsroverkata.models.Rover;
import com.example.marsroverkata.services.IRoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/rover")
public class RoverController {
    @Autowired
    IRoverService roverService;

    @GetMapping
    public ResponseEntity<Rover> getRover() {
        return roverService.getRover();
    }

    @PostMapping
    public ResponseEntity<Rover> createRover(@RequestBody RoverDto roverDto, UriComponentsBuilder uriComponentsBuilder) {
        return roverService.createRover(roverDto, uriComponentsBuilder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rover> updateRover(@PathVariable Long id, @RequestBody RoverDto roverDto) {
        return roverService.updateRover(id, roverDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRover(@PathVariable Long id) {
        return roverService.deleteRover(id);
    }

    @PostMapping("/command")
    @ResponseBody
    public ResponseEntity<Rover> sendCommand(@RequestBody CommandDto commandDto) {
        return roverService.sendCommand(commandDto);
    }
}
