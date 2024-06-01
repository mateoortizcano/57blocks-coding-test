package com.music.app.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.ports.IUserAccountRepository;
import com.music.app.domain.ports.TokenGenerator;
import com.music.app.infrastructure.converters.UserAccountDataConverter;
import com.music.app.infrastructure.wrappers.TokenWrapper;
import com.music.app.infrastructure.wrappers.UserAccountDataWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private IUserAccountRepository userAccountRepository;

    @Test
    public void createAnUserAccountSuccessfully() throws Exception {
        String email = "email@email.com";
        String password = "PasswordNew]";
        UserAccountDataWrapper userAccountDataWrapper = new UserAccountDataWrapper(
                email, password
        );
        mocMvc.perform(post(
                "/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userAccountDataWrapper))
        ).andExpect(
                status().isCreated()
        ).andReturn();
        Optional<UserAccountDto> userAccountByEmail = userAccountRepository.findUserAccountByEmail(email);
        Assertions.assertTrue(userAccountByEmail.isPresent());
        Assertions.assertEquals(password, userAccountByEmail.get().password());
    }

    @Test
    public void AuthenticateUserSuccessfully() throws Exception {
        UserAccountDataWrapper userAccountDataWrapper = new UserAccountDataWrapper(
                "jsmith@example.com",
                "secondPasswordNew]"
        );
        MvcResult mvcResult = mocMvc.perform(post(
                "/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userAccountDataWrapper))
        ).andExpect(
                status().is2xxSuccessful()
        ).andReturn();
        String resultInJson = mvcResult.getResponse().getContentAsString();
        TokenWrapper returnedToken = objectMapper.readValue(resultInJson, TokenWrapper.class);
        Assertions.assertTrue(
                this.tokenGenerator.isTokenValid(returnedToken.token(), userAccountDataWrapper.email())
        );
    }
}
