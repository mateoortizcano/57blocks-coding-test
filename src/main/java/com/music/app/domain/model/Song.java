package com.music.app.domain.model;

import com.music.app.domain.validators.ArgumentsValidator;

public record Song(String title, String genre, long size, String author, String album, boolean isPublic, String idCreator) {
    public static final String TITTLE_IS_REQUIRED = "Tittle is required.";
    public static final String SIZE_NEEDS_TO_BE_BIGGER_THAN = "Size needs to be bigger than";
    public static final String AUTHOR_IS_REQUIRED = "Author is required";
    public static final String ID_OF_THE_USER_ACCOUNT_CREATOR_IS_MANDATORY = "Id of the user account creator is mandatory";

    public Song {
        ArgumentsValidator.verifyNotNullOrEmpty(title, TITTLE_IS_REQUIRED);
        ArgumentsValidator.verifyIsBiggerThan(size, 0L, SIZE_NEEDS_TO_BE_BIGGER_THAN);
        ArgumentsValidator.verifyNotNullOrEmpty(author, AUTHOR_IS_REQUIRED);
        ArgumentsValidator.verifyNotNullOrEmpty(idCreator, ID_OF_THE_USER_ACCOUNT_CREATOR_IS_MANDATORY);
    }

}
