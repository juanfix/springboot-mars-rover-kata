package com.example.marsroverkata.helpers;

import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public abstract class SimulateMockMvcRequest {
    @Autowired
    private MockMvc mvc;
    protected MockHttpServletResponse simulateGet(String url) throws Exception {
        var response = mvc.perform(MockMvcRequestBuilders.get(url)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        return response;
    }

    protected MockHttpServletResponse simulatePost(String url, String jsonTester) throws Exception {
        var response = mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTester))
                .andReturn()
                .getResponse();

        return response;
    }

    protected MockHttpServletResponse simulatePut(String url, String jsonTester) throws Exception {
        var response = mvc.perform(MockMvcRequestBuilders.put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTester))
                .andReturn()
                .getResponse();

        return response;
    }

    protected MockHttpServletResponse simulateDelete(String url) throws Exception {
        var response = mvc.perform(MockMvcRequestBuilders.delete(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        return response;
    }

}
