package com.music.app.domain.model;

import com.music.app.domain.validators.ArgumentsValidator;

public record UpdateSongData(String title, String genre, Long size, String author, String album, Boolean isPublic) {
    public static final String SIZE_NEEDS_TO_BE_BIGGER_THAN = "Size needs to be bigger than %s";
    public static final String TITTLE_CAN_T_BE_EMPTY = "Tittle can't be empty.";
    public static final String AUTHOR_CAN_T_BE_EMPTY = "author can't be empty.";

    public UpdateSongData {
        if (title != null)
            ArgumentsValidator.verifyNotEmpty(title, TITTLE_CAN_T_BE_EMPTY);
        if (size != null)
            ArgumentsValidator.verifyIsBiggerThan(size, 0L, String.format(SIZE_NEEDS_TO_BE_BIGGER_THAN, 0L));
        if (author != null)
            ArgumentsValidator.verifyNotEmpty(author, AUTHOR_CAN_T_BE_EMPTY);
    }

}
