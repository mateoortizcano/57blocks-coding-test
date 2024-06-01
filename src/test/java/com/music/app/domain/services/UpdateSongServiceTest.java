package com.music.app.domain.services;

import com.music.app.TestingExceptionAssertions;
import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.exceptions.NotAllowedOperationException;
import com.music.app.domain.exceptions.NotFoundExxeption;
import com.music.app.domain.exceptions.NullOrEmptyValueExeption;
import com.music.app.domain.model.UpdateSongData;
import com.music.app.domain.ports.ISongRepository;
import com.music.app.domain.services.song.UpdateSongService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.music.app.domain.services.song.UpdateSongService.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UpdateSongServiceTest {

    @Mock
    private ISongRepository songRepository;

    @InjectMocks
    private UpdateSongService updateSongService;
    private AutoCloseable autoCloseable;

    @BeforeAll
    public void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenSongIdIsNullThenExceptionIsThrown() {
        //Arrange
        String songId = null;
        String idRequestingUser = "ID";
        UpdateSongData updateSongData = new UpdateSongData("Title", "Pop", null, null, null, null);
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.updateSongService.execute(songId, updateSongData, idRequestingUser), NullOrEmptyValueExeption.class,
                THE_SONG_ID_TO_UPDATE_IS_REQUIRED
        );
    }

    @Test
    public void whenSongIdIsEmptyThenExceptionIsThrown() {
        //Arrange
        String songId = "";
        String idRequestingUser = "ID";
        UpdateSongData updateSongData = new UpdateSongData("Title", "Pop", null, null, null, null);
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.updateSongService.execute(songId, updateSongData, idRequestingUser), NullOrEmptyValueExeption.class,
                THE_SONG_ID_TO_UPDATE_IS_REQUIRED
        );
    }

    @Test
    public void whenUserIdIsEmptyThenExceptionIsThrown() {
        //Arrange
        String songId = "ID";
        String idRequestingUser = "";
        UpdateSongData updateSongData = new UpdateSongData("Title", "Pop", null, null, null, null);
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.updateSongService.execute(songId, updateSongData, idRequestingUser), NullOrEmptyValueExeption.class,
                THE_AUTHENTICATED_USER_IS_REQUIRED
        );
    }

    @Test
    public void whenUserIdIsNullThenExceptionIsThrown() {
        //Arrange
        String songId = "ID";
        String idRequestingUser = null;
        UpdateSongData updateSongData = new UpdateSongData("Title", "Pop", null, null, null, null);
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.updateSongService.execute(songId, updateSongData, idRequestingUser), NullOrEmptyValueExeption.class,
                THE_AUTHENTICATED_USER_IS_REQUIRED
        );
    }

    @Test
    public void whenSongDoesNotExistThenExceptionIsThrown() {
        //Arrange
        String songId = "ID";
        String idRequestingUser = "ID";
        UpdateSongData updateSongData = new UpdateSongData("Title", "Pop", null, null, null, null);
        Mockito.when(this.songRepository.findById(songId)).thenReturn(Optional.empty());
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.updateSongService.execute(songId, updateSongData, idRequestingUser), NotFoundExxeption.class,
                THE_SONG_YOU_ARE_TRYING_TO_UPDATE_DOES_NOT_EXIST
        );
    }

    @Test
    public void whenSongExistButIsNotCreatedBytheUserThenExceptionIsThrown() {
        //Arrange
        String songId = "ID";
        String idRequestingUser = "ID";
        UpdateSongData updateSongData = new UpdateSongData("Title", "Pop", null, null, null, null);
        Mockito.when(this.songRepository.findById(songId)).thenReturn(Optional.of(
                new SongDto("ID", "Tittle", "Pop", 3L, "Author", "Album", true, "AnotherID")
        ));
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.updateSongService.execute(songId, updateSongData, idRequestingUser), NotAllowedOperationException.class,
                THIS_SONG_IS_NOT_CREATED_BY_YOU_SO_YOU_CAN_NOT_MODIFY_IT
        );
    }

    @AfterAll
    public void close() {
        try {
            this.autoCloseable.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
