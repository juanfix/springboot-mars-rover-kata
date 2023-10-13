package com.example.marsroverkata.controllers;

import com.example.marsroverkata.dto.CommandDto;
import com.example.marsroverkata.dto.RoverDto;
import com.example.marsroverkata.helpers.SimulateMockMvcRequest;
import com.example.marsroverkata.models.Rover;
import com.example.marsroverkata.services.IRoverService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@SuppressWarnings("all")
public class RoverControllerTest extends SimulateMockMvcRequest {
    @Autowired
    private JacksonTester<RoverDto> roverDtoJacksonTester;
    @Autowired
    private JacksonTester<CommandDto> commandDtoJacksonTester;
    @MockBean
    private IRoverService roverService;

    @Test
    public void whenGetRover_returnRoverObject() throws Exception {
        // given
        RoverDto roverDto = new RoverDto();

        // when
        when(roverService.getRover()).thenReturn(ResponseEntity.ok(new Rover()));

        MockHttpServletResponse response = simulateGet("/api/rover");

        // then
        verify(roverService, times(1)).getRover();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(roverDtoJacksonTester.write(roverDto).getJson());
    }

    @Test
    public void whenCreateRover_returnRoverObject() throws Exception {
        // given
        RoverDto roverDto = new RoverDto();

        // when
        when(roverService.createRover(any(), any())).thenReturn(ResponseEntity.ok(new Rover()));
        MockHttpServletResponse response = simulatePost("/api/rover", roverDtoJacksonTester.write(roverDto).getJson());

        // then
        verify(roverService, times(1)).createRover(any(), any());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(roverDtoJacksonTester.write(roverDto).getJson());
    }

    @Test
    public void whenUpdateRover_returnRoverObject() throws Exception {
        // given
        RoverDto roverDto = new RoverDto();

        // when
        when(roverService.updateRover(any(), any())).thenReturn(ResponseEntity.ok(new Rover()));
        MockHttpServletResponse response = simulatePut("/api/rover/1", roverDtoJacksonTester.write(roverDto).getJson());

        // then
        verify(roverService, times(1)).updateRover(any(), any());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(roverDtoJacksonTester.write(roverDto).getJson());
    }

    @Test
    public void whenSendCommand_callService() throws Exception {
        // given
        CommandDto commandDto = new CommandDto();
        List<String> commandsList = new ArrayList<>();
        commandsList.add("F");
        commandDto.setCommands(commandsList);

        // when
        MockHttpServletResponse response = simulatePost("/api/rover/command", commandDtoJacksonTester.write(commandDto).getJson());

        // then
        verify(roverService, times(1)).sendCommand(commandDto);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
