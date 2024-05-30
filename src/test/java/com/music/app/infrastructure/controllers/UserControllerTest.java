package com.music.app.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.app.infrastructure.wrappers.UserAccountDataWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void createAnUserAccountSuccessfully() throws Exception {
        UserAccountDataWrapper userAccountDataWrapper = new UserAccountDataWrapper();
        userAccountDataWrapper.setEmail("email@email.com");
        userAccountDataWrapper.setPassword("PasswordNew]");
        mocMvc.perform(post(
                "/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userAccountDataWrapper))
        ).andExpect(
                status().is2xxSuccessful()
        ).andReturn();
    }
}
