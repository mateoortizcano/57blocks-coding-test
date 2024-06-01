package com.music.app.domain.model;

import com.music.app.TestingExceptionAssertions;
import com.music.app.domain.exceptions.InvalidValueException;
import com.music.app.domain.exceptions.NullOrEmptyValueExeption;
import org.junit.jupiter.api.Test;

import static com.music.app.domain.model.UpdateSongData.*;

public class UpdateSongTest {

    @Test
    public void whenTittleIsEmptyThenExceptionIsThrown() {
        String title = "";
        String genre = "pop";
        long size = 3L;
        String author = "Singer";
        String album = "Album";
        boolean isPublic = false;
        TestingExceptionAssertions.assertThrows(
                () -> new UpdateSongData(title, genre, size, author, album, isPublic), NullOrEmptyValueExeption.class,
                TITTLE_CAN_T_BE_EMPTY
        );
    }

    @Test
    public void whenAuthorIsEmptyThenExceptionIsThrown() {
        String title = "Song1";
        String genre = "pop";
        long size = 3L;
        String author = "";
        String album = "Album";
        boolean isPublic = false;
        TestingExceptionAssertions.assertThrows(
                () -> new UpdateSongData(title, genre, size, author, album, isPublic), NullOrEmptyValueExeption.class,
                AUTHOR_CAN_T_BE_EMPTY
        );
    }

    @Test
    public void whenSizeIsZeroThenExceptionIsThrown() {
        String title = "Song1";
        String genre = "pop";
        long size = 0L;
        String author = "Singer";
        String album = "Album";
        boolean isPublic = false;
        TestingExceptionAssertions.assertThrows(
                () -> new UpdateSongData(title, genre, size, author, album, isPublic), InvalidValueException.class,
                String.format(SIZE_NEEDS_TO_BE_BIGGER_THAN, 0L)
        );
    }

    @Test
    public void whenSizeIsLessThanZeroThenExceptionIsThrown() {
        String title = "Song1";
        String genre = "pop";
        long size = -4L;
        String author = "Singer";
        String album = "Album";
        boolean isPublic = false;
        TestingExceptionAssertions.assertThrows(
                () -> new UpdateSongData(title, genre, size, author, album, isPublic), InvalidValueException.class,
                String.format(SIZE_NEEDS_TO_BE_BIGGER_THAN, 0L)
        );
    }
}
