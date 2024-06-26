package com.music.app.domain.model;

import com.music.app.domain.validators.ArgumentsValidator;

public record UserAccountData(String email, String password) {
    public static final String EMAIL_IS_REQUIRED = "Email is required.";
    public static final String INVALID_E_MAIL_FORMAT = "The email format is not valid.";
    public static final String PASSWORD_IS_REQUIRED = "Password is required.";
    public static final String INVALID_PASSWORD_FORMAT = "The password format is not valid. Please include at least 10 characters, one lowercase letter, one uppercase letter and one of the following characters: !, @, #, ? or ].";

    public UserAccountData {
        ArgumentsValidator.verifyNotNullOrEmpty(email, EMAIL_IS_REQUIRED);
        ArgumentsValidator.verifyNotNullOrEmpty(password, PASSWORD_IS_REQUIRED);
        ArgumentsValidator.verifyEmailFormat(email, INVALID_E_MAIL_FORMAT);
        ArgumentsValidator.verifyPasswordFormat(password, INVALID_PASSWORD_FORMAT);
    }
}
