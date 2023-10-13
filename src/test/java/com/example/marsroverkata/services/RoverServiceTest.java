package com.example.marsroverkata.services;

import com.example.marsroverkata.dto.CommandDto;
import com.example.marsroverkata.dto.ObstacleDto;
import com.example.marsroverkata.enums.Direction;
import com.example.marsroverkata.infra.validations.rover.RoverMovementValidator;
import com.example.marsroverkata.models.Rover;
import com.example.marsroverkata.repository.RoverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureJsonTesters
class RoverServiceTest {
    @Mock
    private RoverRepository roverRepository;

    @Mock
    private RoverMovementValidator roverMovementValidator;

    @InjectMocks
    private RoverService roverService;

    @Autowired
    private JacksonTester<Rover> roverJacksonTester;

    private Rover rover;

    private CommandDto commandDto;

    private List<String> commandsList;

    @BeforeEach
    public void setup(){
        rover = new Rover();
        rover.setId(1L);
        rover.setXPosition(5);
        rover.setYPosition(4);
        rover.setDirection(Direction.NORTH);

        commandDto = new CommandDto();
        commandsList = new ArrayList<>();
        commandsList.add("F");
        commandDto.setCommands(commandsList);
    }

    @Test
    void whenSendCommand_thenCallService() {
        // given
        List<Rover> roverList = new ArrayList<>();
        roverList.add(rover);

        given(roverRepository.save(rover)).willReturn(rover);
        given(roverRepository.findAll()).willReturn(roverList);
        given(roverMovementValidator.validate(any(), any())).willReturn(true);

        // when
        roverService.sendCommand(commandDto);

        // then
        Rover finalRover = new Rover();
        finalRover.setId(1L);
        finalRover.setXPosition(5);
        finalRover.setYPosition(3);
        finalRover.setDirection(Direction.NORTH);

        verify(roverRepository, times(1)).save(finalRover);
    }

    @Test
    void whenRoverDirectionIsNorth_turnRight_thenNewDirectionMustBeEast() {
        // given
        List<Rover> roverList = new ArrayList<>();
        roverList.add(rover);

        commandsList.add("R");

        given(roverRepository.save(rover)).willReturn(rover);
        given(roverRepository.findAll()).willReturn(roverList);
        given(roverMovementValidator.validate(any(), any())).willReturn(true);

        // when
        var response = roverService.sendCommand(commandDto);

        // then
        assertThat(Objects.requireNonNull(response.getBody()).getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    void whenRoverDirectionIsNorth_sendFAndTurnLeftAndSendBBB_thenNewDirectionMustBeWestAndHasNewPosition() throws IOException {
        // given
        List<Rover> roverList = new ArrayList<>();
        roverList.add(rover);

        commandsList.add("L");
        commandsList.add("B");
        commandsList.add("B");
        commandsList.add("B");

        given(roverRepository.save(rover)).willReturn(rover);
        given(roverRepository.findAll()).willReturn(roverList);
        given(roverMovementValidator.validate(any(), any())).willReturn(true);

        // when
        var response = roverService.sendCommand(commandDto);

        // then
        Rover finalRover = new Rover();
        finalRover.setId(1L);
        finalRover.setXPosition(2);
        finalRover.setYPosition(3);
        finalRover.setDirection(Direction.WEST);

        assertThat(roverJacksonTester.write(response.getBody()).getJson()).isEqualTo(roverJacksonTester.write(finalRover).getJson());
    }

}
