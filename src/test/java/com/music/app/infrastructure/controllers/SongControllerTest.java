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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    private String getBearerToken(String useridInDb, String email, String password) {
        UserAccountDto userAccountDto = new UserAccountDto(useridInDb, email, password);
        return "Bearer " + this.tokenGenerator.generateToken(userAccountDto).token();
    }

    @Test
    public void createASongSuccessfully() throws Exception {
        String title = "Shape of You";
        SongWrapper song = new SongWrapper(title, "Pop", 5000, "Ed Sheeran",
                "Divide", false);
        String token = getBearerToken("1", "jsmith@example.com", "secondPasswordNew]");
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


    @Test
    public void readAllPublicSongs() throws Exception {
        //Arrange
        String token = getBearerToken("1", "jsmith@example.com", "secondPasswordNew]");
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
        Assertions.assertEquals(3, songs.length);
    }

    @Test
    public void readAllSongsCreatedByMe() throws Exception {
        //Arrange
        String token = getBearerToken("2", "amiller@example.com", "secondPasswordNew]");
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
        Assertions.assertEquals(4, songs.length);
    }
}
