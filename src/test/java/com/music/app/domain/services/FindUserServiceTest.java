package com.music.app.domain.services;

import com.music.app.TestingExceptionAssertions;
import com.music.app.domain.exceptions.InvalidCredentialsException;
import com.music.app.domain.exceptions.NullOrEmptyValueExeption;
import com.music.app.domain.ports.IUserAccountRepository;
import com.music.app.infrastructure.services.FindUserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.music.app.infrastructure.services.FindUserService.EMAIL_IS_REQUIRED;
import static com.music.app.infrastructure.services.FindUserService.THERE_IS_NO_ACCOUNT_REGISTERED_WITH_EMAIL_S;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindUserServiceTest {

    @Mock
    private IUserAccountRepository userAccountRepository;
    @InjectMocks
    private FindUserService findUserService;
    private AutoCloseable autoCloseable;

    @BeforeAll
    public void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenEmailProvidedIsNullThenExceptionIsThrown() {
        //Arrange
        String email = null;
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.findUserService.findUser(email), NullOrEmptyValueExeption.class,
                EMAIL_IS_REQUIRED
        );
    }

    @Test
    public void whenEmailProvidedIsEmptyThenExceptionIsThrown() {
        //Arrange
        String email = "";
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.findUserService.findUser(email), NullOrEmptyValueExeption.class,
                EMAIL_IS_REQUIRED
        );
    }

    @Test
    public void whenUserIsNotFoundInRepositoryThrowsException() {
        //Arrange
        String email = "email@email.com";
        Mockito.when(this.userAccountRepository.findUserAccountByEmail(email)).thenReturn(
                Optional.empty()
        );
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.findUserService.findUser(email), InvalidCredentialsException.class,
                String.format(THERE_IS_NO_ACCOUNT_REGISTERED_WITH_EMAIL_S, email)
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
