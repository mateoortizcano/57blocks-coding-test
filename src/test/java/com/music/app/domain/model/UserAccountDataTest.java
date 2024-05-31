package com.music.app.domain.model;

import com.music.app.TestingExceptionAssertions;
import com.music.app.domain.exceptions.InvalidValueException;
import com.music.app.domain.exceptions.NullOrEmptyValueExeption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.music.app.domain.model.UserAccountData.*;

public class UserAccountDataTest {
    @Test
    public void whenEmailIsNullThenExceptionIsThrown() {
        TestingExceptionAssertions.assertThrows(
                () -> new UserAccountData(null, "password"), NullOrEmptyValueExeption.class, EMAIL_IS_REQUIRED
        );
    }

    @Test
    public void whenEmailIsEmptyThenExceptionIsThrown() {
        TestingExceptionAssertions.assertThrows(
                () -> new UserAccountData("", "password"), NullOrEmptyValueExeption.class, EMAIL_IS_REQUIRED
        );
    }

    @Test
    public void whenPasswordIsNullThenExceptionIsThrown() {
        TestingExceptionAssertions.assertThrows(
                () -> new UserAccountData("email@email.com", null), NullOrEmptyValueExeption.class, PASSWORD_IS_REQUIRED
        );
    }

    @Test
    public void whenPasswordIsEmptyThenExceptionIsThrown() {
        TestingExceptionAssertions.assertThrows(
                () -> new UserAccountData("email@email.com", ""), NullOrEmptyValueExeption.class, PASSWORD_IS_REQUIRED
        );
    }

    @Test
    public void whenEmailFormatIsIncorrectThenExceptionIsThrown() {
        TestingExceptionAssertions.assertThrows(
                () -> new UserAccountData("email@email", "password"), InvalidValueException.class, INVALID_E_MAIL_FORMAT
        );
    }

    @Test
    public void whenEmailFormatIsIncorrectThenExceptionIsThrown2() {
        TestingExceptionAssertions.assertThrows(
                () -> new UserAccountData("emailemail.com", "password"), InvalidValueException.class, INVALID_E_MAIL_FORMAT
        );
    }

    @Test
    public void whenPasswordLengthIsLessThanExpectedThenExceptionIsThrown() {
        TestingExceptionAssertions.assertThrows(
                () -> new UserAccountData("email@email.com", "pAss!@#?"), InvalidValueException.class, INVALID_PASSWORD_FORMAT
        );
    }

    @Test
    public void whenPasswordDontContainsAnUpperCaseLetterThenExceptionIsThrown() {
        TestingExceptionAssertions.assertThrows(
                () -> new UserAccountData("email@email.com", "password!@#?]"), InvalidValueException.class, INVALID_PASSWORD_FORMAT
        );
    }

    @Test
    public void whenPasswordDontContainsAnLowerCaseLetterThenExceptionIsThrown() {
        TestingExceptionAssertions.assertThrows(
                () -> new UserAccountData("email@email.com", "PASSWORD!@#?]"), InvalidValueException.class, INVALID_PASSWORD_FORMAT
        );
    }

    @Test
    public void whenPasswordDontContainsAtLeastOneSpecialCharacterThenExceptionIsThrown() {
        TestingExceptionAssertions.assertThrows(
                () -> new UserAccountData("email@email.com", "PasswordGiven"), InvalidValueException.class, INVALID_PASSWORD_FORMAT
        );
    }

    @Test
    public void whenDataIsCorrectThenObjectIsInstantiated() {
        String email = "email@email.com";
        String password = "Passwordiven!";
        UserAccountData userAccountData = new UserAccountData(email, password);
        Assertions.assertEquals(email, userAccountData.email());
        Assertions.assertEquals(password, userAccountData.password());

    }
}
