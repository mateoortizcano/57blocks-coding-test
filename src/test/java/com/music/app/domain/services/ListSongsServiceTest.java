package com.music.app.domain.services;

import com.music.app.TestingExceptionAssertions;
import com.music.app.domain.exceptions.InvalidValueException;
import com.music.app.domain.exceptions.NullOrEmptyValueExeption;
import com.music.app.domain.ports.ISongRepository;
import com.music.app.domain.services.song.ListSongsService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.music.app.domain.services.song.ListSongsService.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ListSongsServiceTest {
    @Mock
    private ISongRepository songRepository;
    @InjectMocks
    private ListSongsService listSongsService;
    private AutoCloseable autoCloseable;

    @BeforeAll
    public void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenPageNumberIsNegativeInGetAllThenThrowException() {
        //Arrange
        int pageNumber = -1;
        int pageSize = 1;
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.listSongsService.getAllPublicSongs(pageNumber, pageSize), InvalidValueException.class,
                PAGE_NUMBER_SHOULD_BE_0_OR_BIGGER
        );
    }

    @Test
    public void whenPageSizeIsZeroInGetAllThenThrowException() {
        //Arrange
        int pageNumber = 0;
        int pageSize = 0;
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.listSongsService.getAllPublicSongs(pageNumber, pageSize), InvalidValueException.class,
                PAGE_SIZE_SHOULD_BE_1_OR_BIGGER
        );
    }

    @Test
    public void whenPageNumberIsNegativeInGetMineThenThrowException() {
        //Arrange
        int pageNumber = -1;
        int pageSize = 1;
        String requestingUserId = "ID1";
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.listSongsService.getAllMySongs(requestingUserId, pageNumber, pageSize), InvalidValueException.class,
                PAGE_NUMBER_SHOULD_BE_0_OR_BIGGER
        );
    }

    @Test
    public void whenPageSizeIsZeroInGetMineThenThrowException() {
        //Arrange
        int pageNumber = 0;
        int pageSize = 0;
        String requestingUserId = "ID1";
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.listSongsService.getAllMySongs(requestingUserId, pageNumber, pageSize), InvalidValueException.class,
                PAGE_SIZE_SHOULD_BE_1_OR_BIGGER
        );
    }

    @Test
    public void whenRequestinUserIDIsNullInGetMineThenThrowException() {
        //Arrange
        int pageNumber = 0;
        int pageSize = 0;
        String requestingUserId = null;
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.listSongsService.getAllMySongs(requestingUserId, pageNumber, pageSize), NullOrEmptyValueExeption.class,
                THE_AUTHENTICATED_USER_IS_NECESSARY
        );
    }

    @Test
    public void whenRequestinUserIDIsEmptyInGetMineThenThrowException() {
        //Arrange
        int pageNumber = 0;
        int pageSize = 0;
        String requestingUserId = "";
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.listSongsService.getAllMySongs(requestingUserId, pageNumber, pageSize), NullOrEmptyValueExeption.class,
                THE_AUTHENTICATED_USER_IS_NECESSARY
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
