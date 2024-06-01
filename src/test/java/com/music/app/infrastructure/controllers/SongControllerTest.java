package com.music.app.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.ports.ISongRepository;
import com.music.app.domain.ports.IUserAccountRepository;
import com.music.app.domain.ports.TokenGenerator;
import com.music.app.infrastructure.converters.UserAccountDataConverter;
import com.music.app.infrastructure.wrappers.SongWrapper;
import com.music.app.infrastructure.wrappers.UserAccountDataWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SongControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;
    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private ISongRepository songRepository;
    @Autowired
    private IUserAccountRepository userAccountRepository;

    @Test
    public void createASongSuccessfully() throws Exception {
        String title = "Shape of You";
        SongWrapper song = new SongWrapper(title, "Pop", 5000, "Ed Sheeran",
                "Divide", true);
        String email = "email-test3@email.com";
        String password = "PasswordNew]";
        UserAccountDataWrapper userAccountDataWrapper = new UserAccountDataWrapper(
                email, password
        );
        this.performCreateAccount(userAccountDataWrapper);
        UserAccountDto userAccountDto = new UserAccountDto("ID1", email, password);
        String token = "Bearer " + this.tokenGenerator.generateToken(userAccountDto).token();
        mocMvc.perform(post(
                "/songs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(song))
                .header(HttpHeaders.AUTHORIZATION, token)
        ).andExpect(
                status().isCreated()
        ).andReturn();
        Assertions.assertTrue(songRepository.findByTitle(title).isPresent());
    }

    private void performCreateAccount(UserAccountDataWrapper userAccountDataWrapper) {
        this.userAccountRepository.createUserAccount(UserAccountDataConverter.convertToDomain(userAccountDataWrapper));
    }

    @Test
    public void readAllElements(){

    }


}
