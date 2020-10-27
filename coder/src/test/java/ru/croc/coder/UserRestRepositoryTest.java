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

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class UserRestRepositoryTest {

    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    public void testGetUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

   @Test
    public void testPostRequest() throws Exception {
        Map<String, String> stringObjectMap = new HashMap<>(Map.of());
        stringObjectMap.put("email", "b.boginskij@voskhod.ru");
        stringObjectMap.put("firstName", "Bogdan");
        stringObjectMap.put("lastName", "Boginskiy");
        stringObjectMap.put("password", "1234");
        JSONObject jsonObject = new JSONObject(stringObjectMap);
        mockMvc.perform(MockMvcRequestBuilders.post("/users").content(jsonObject.toJSONString())).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
