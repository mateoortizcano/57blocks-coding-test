package com.music.app.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.model.Song;
import com.music.app.domain.ports.ISongRepository;
import com.music.app.domain.ports.IUserAccountRepository;
import com.music.app.domain.ports.TokenGenerator;
import com.music.app.infrastructure.converters.UserAccountDataConverter;
import com.music.app.infrastructure.wrappers.SongWrapper;
import com.music.app.infrastructure.wrappers.UserAccountDataWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    private String token;
    private String createdUserId;

    @BeforeAll
    public void setup() {
        token = getBearerToken();
    }

    @Rollback(value = true)
    @Test
    public void createASongSuccessfully() throws Exception {
        String title = "Shape of You";
        SongWrapper song = new SongWrapper(title, "Pop", 5000, "Ed Sheeran",
                "Divide", false);

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

    private String getBearerToken() {
        String email = "email-test3@email.com";
        String password = "PasswordNew]";
        UserAccountDataWrapper userAccountDataWrapper = new UserAccountDataWrapper(
                email, password
        );
        createdUserId = this.performCreateAccount(userAccountDataWrapper);
        UserAccountDto userAccountDto = new UserAccountDto("ID1", email, password);
        return "Bearer " + this.tokenGenerator.generateToken(userAccountDto).token();
    }

    private String performCreateAccount(UserAccountDataWrapper userAccountDataWrapper) {
        return this.userAccountRepository.createUserAccount(UserAccountDataConverter.convertToDomain(userAccountDataWrapper));
    }

    @Rollback(value = true)
    @Test
    public void readAllPublicSongs() throws Exception {
        //Arrange
        String myId = createdUserId;
        Song publicSongMine = new Song("Shape of You 1", "Pop", 5000, "Ed Sheeran", "Divide", true, myId);
        Song publicSongMine2 = new Song("Shape of You 1.1", "Pop", 5000, "Ed Sheeran", "Divide", true, myId);
        Song privateSongMine = new Song("Shape of You 2", "Pop", 5000, "Ed Sheeran", "Divide", false, myId);
        this.songRepository.create(publicSongMine);
        this.songRepository.create(publicSongMine2);
        this.songRepository.create(privateSongMine);
        //Act
        MvcResult mvcResult = mocMvc.perform(get(
                "/songs?page-number=0&page-size=10")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
        ).andExpect(
                status().is2xxSuccessful()
        ).andReturn();
        String resultInJson = mvcResult.getResponse().getContentAsString();
        SongDto[] songs = objectMapper.readValue(resultInJson, SongDto[].class);
        //Assert
        Assertions.assertEquals(1, songs.length);
    }

    @Rollback(value = true)
    @Test
    @Transactional
    public void readAllSongsCreatedByMe() throws Exception {
        //Arrange
        String myId = createdUserId;
        Song publicSongMine = new Song("Shape of You 1", "Pop", 5000, "Ed Sheeran", "Divide", true, myId);
        Song publicSongMine2 = new Song("Shape of You 1.1", "Pop", 5000, "Ed Sheeran", "Divide", true, myId);
        Song privateSongMine = new Song("Shape of You 2", "Pop", 5000, "Ed Sheeran", "Divide", false, myId);
        this.songRepository.create(publicSongMine);
        this.songRepository.create(publicSongMine2);
        this.songRepository.create(privateSongMine);
        //Act
        MvcResult mvcResult = mocMvc.perform(get(
                "/created-by-me?page-number=0&page-size=10")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
        ).andExpect(
                status().is2xxSuccessful()
        ).andReturn();
        String resultInJson = mvcResult.getResponse().getContentAsString();
        SongDto[] songs = objectMapper.readValue(resultInJson, SongDto[].class);
        //Assert
        Assertions.assertEquals(3, songs.length);
    }
}
