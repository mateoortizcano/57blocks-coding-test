package com.music.app.domain.validators;

import com.music.app.domain.exceptions.InvalidValueException;
import com.music.app.domain.exceptions.NullOrEmptyValueExeption;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgumentsValidator {

    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#?\\]]).{10,}$";

    private ArgumentsValidator() {
    }

    public static void verifyNotNullOrEmpty(String value, String message) {
        if (value == null || value.isEmpty()) {
            throw new NullOrEmptyValueExeption(message);
        }
    }

    public static void verifyNotEmpty(String value, String message) {
        if (value == null || value.isEmpty()) {
            throw new NullOrEmptyValueExeption(message);
        }
    }

    public static void verifyEmailFormat(String email, String message) {
        verifyRegexExpression(EMAIL_REGEX, email, message);
    }

    public static void verifyPasswordFormat(String password, String message) {
        verifyRegexExpression(PASSWORD_REGEX, password, message);
    }

    private static void verifyRegexExpression(String regex, String value, String message) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new InvalidValueException(message);
        }
    }

    public static void verifyIsBiggerThan(Long number, Long limit, String message) {
        if (number.compareTo(limit) <= 0) {
            throw new InvalidValueException(message);
        }
    }

    public static void verifyIsBiggerThan(int number, int limit, String message) {
        if (number < limit ) {
            throw new InvalidValueException(message);
        }
    }
}
