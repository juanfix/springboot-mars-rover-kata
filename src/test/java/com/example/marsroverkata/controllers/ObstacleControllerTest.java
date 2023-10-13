package com.example.marsroverkata.controllers;

import com.example.marsroverkata.dto.ObstacleDto;
import com.example.marsroverkata.helpers.SimulateMockMvcRequest;
import com.example.marsroverkata.models.Obstacle;
import com.example.marsroverkata.services.IObstacleService;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@SuppressWarnings("all")
public class ObstacleControllerTest extends SimulateMockMvcRequest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<List<Obstacle>> listJacksonTester;
    @Autowired
    private JacksonTester<ObstacleDto> obstacleDtoJacksonTester;
    @MockBean
    private IObstacleService obstacleService;

    @Test
    public void whenGetObstacle_returnObstacleListObject() throws Exception {
        // given
        ObstacleDto obstacleDto = new ObstacleDto();
        List<Obstacle> obstacleList = new ArrayList<>();
        obstacleList.add(new Obstacle());

        // when
        when(obstacleService.findAll()).thenReturn(ResponseEntity.ok(obstacleList));

        MockHttpServletResponse response = simulateGet("/api/obstacle");

        // then
        verify(obstacleService, times(1)).findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(listJacksonTester.write(obstacleList).getJson());
    }

    @Test
    public void whenCreateObstacle_returnObstacleObject() throws Exception {
        // given
        ObstacleDto obstacleDto = new ObstacleDto();

        // when
        when(obstacleService.createObstacle(any(), any())).thenReturn(ResponseEntity.ok(new Obstacle()));
        MockHttpServletResponse response = simulatePost("/api/obstacle", obstacleDtoJacksonTester.write(obstacleDto).getJson());

        // then
        verify(obstacleService, times(1)).createObstacle(any(), any());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(obstacleDtoJacksonTester.write(obstacleDto).getJson());
    }

    @Test
    public void whenUpdateObstacle_returnObstacleObject() throws Exception {
        // given
        ObstacleDto obstacleDto = new ObstacleDto();

        // when
        when(obstacleService.updateObstacle(any(), any())).thenReturn(ResponseEntity.ok(new Obstacle()));
        MockHttpServletResponse response = simulatePut("/api/obstacle/1", obstacleDtoJacksonTester.write(obstacleDto).getJson());

        // then
        verify(obstacleService, times(1)).updateObstacle(any(), any());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(obstacleDtoJacksonTester.write(obstacleDto).getJson());
    }

}
