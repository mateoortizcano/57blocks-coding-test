package com.music.app.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.ports.ISongRepository;
import com.music.app.domain.ports.TokenGenerator;
import com.music.app.infrastructure.wrappers.SongWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

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

    @Test
    public void createASongSuccessfully() throws Exception {
        SongWrapper song = new SongWrapper();
        String title = "Shape of You";
        song.setTitle(title);
        song.setGenre("Pop");
        song.setSize(5000);
        song.setAuthor("Ed Sheeran");
        song.setAlbum("Divide");
        song.setPublic(true);
        UserAccountDto userAccountDto = new UserAccountDto("ID1", "email@email.com", "PasswordNew]");
        String token = this.tokenGenerator.generateToken(userAccountDto).token();
        mocMvc.perform(post(
                "/songs/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(song))
                .header("Authorization", token)
        ).andExpect(
                status().isCreated()
        ).andReturn();
        Assertions.assertTrue(songRepository.findByTitle(title).isPresent());
    }
}
