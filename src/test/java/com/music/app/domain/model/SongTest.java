package com.music.app.domain.model;

import com.music.app.TestingExceptionAssertions;
import com.music.app.domain.exceptions.InvalidValueException;
import com.music.app.domain.exceptions.NullOrEmptyValueExeption;
import org.junit.jupiter.api.Test;

import static com.music.app.domain.model.Song.*;

public class SongTest {
    @Test
    public void whenTittleIsNullThenExceptionIsThrown() {
        String title = null;
        String genre = "pop";
        long size = 3L;
        String author = "Singer";
        String album = "Album";
        boolean isPublic = false;
        TestingExceptionAssertions.assertThrows(
                () -> new Song(title, genre, size, author, album, isPublic), NullOrEmptyValueExeption.class, TITTLE_IS_REQUIRED
        );
    }

    @Test
    public void whenTittleIsEmptyThenExceptionIsThrown() {
        String title = "";
        String genre = "pop";
        long size = 3L;
        String author = "Singer";
        String album = "Album";
        boolean isPublic = false;
        TestingExceptionAssertions.assertThrows(
                () -> new Song(title, genre, size, author, album, isPublic), NullOrEmptyValueExeption.class, TITTLE_IS_REQUIRED
        );
    }

    @Test
    public void whenAuthorIsNullThenExceptionIsThrown() {
        String title = "Song1";
        String genre = "pop";
        long size = 3L;
        String author = null;
        String album = "Album";
        boolean isPublic = false;
        TestingExceptionAssertions.assertThrows(
                () -> new Song(title, genre, size, author, album, isPublic), NullOrEmptyValueExeption.class, AUTHOR_IS_REQUIRED
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
                () -> new Song(title, genre, size, author, album, isPublic), NullOrEmptyValueExeption.class, AUTHOR_IS_REQUIRED
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
                () -> new Song(title, genre, size, author, album, isPublic), InvalidValueException.class, SIZE_NEEDS_TO_BE_BIGGER_THAN
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
                () -> new Song(title, genre, size, author, album, isPublic), InvalidValueException.class, SIZE_NEEDS_TO_BE_BIGGER_THAN
        );
    }
}
