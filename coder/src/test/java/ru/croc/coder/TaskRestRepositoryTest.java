package ru.croc.coder;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@SpringBootTest
public class TaskRestRepositoryTest {

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    public void testPostTaskRequest() throws Exception {
    }

    @Test
    public void testPutTaskRequest() throws Exception {
        Map<String, String> requestMap = Map.of("description", "Changed description");
        JSONObject requestJson = new JSONObject(requestMap);
        mockMvc.perform(MockMvcRequestBuilders.put("/tasks/1").content(requestJson.toJSONString()))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void testGetTaskRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void testDeleteTaskRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/" ))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
