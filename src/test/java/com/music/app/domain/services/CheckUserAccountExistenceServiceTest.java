package com.music.app.domain.services;

import com.music.app.TestingExceptionAssertions;
import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.exceptions.ExistentAccountException;
import com.music.app.domain.exceptions.NullOrEmptyValueExeption;
import com.music.app.domain.ports.IUserAccountRepository;
import com.music.app.domain.services.user.CheckUserAccountExistenceService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.music.app.domain.services.user.CheckUserAccountExistenceService.EMAIL_IS_REQUIRED;
import static com.music.app.domain.services.user.CheckUserAccountExistenceService.THERE_IS_ALREADY_AN_ACCOUNT_WITH_EMAIL_S;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckUserAccountExistenceServiceTest {

    @Mock
    private IUserAccountRepository userAccountRepository;
    @InjectMocks
    private CheckUserAccountExistenceService checkUserAccountExistenceService;
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
                () -> this.checkUserAccountExistenceService.checkIfExists(email), NullOrEmptyValueExeption.class,
                EMAIL_IS_REQUIRED
        );
    }

    @Test
    public void whenEmailProvidedIsEmptyThenExceptionIsThrown() {
        //Arrange
        String email = "";
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.checkUserAccountExistenceService.checkIfExists(email), NullOrEmptyValueExeption.class,
                EMAIL_IS_REQUIRED
        );
    }

    @Test
    public void whenUserAccountExistsThenExceptionIsThrown() {
        //Arrange
        String email = "email@email.com";
        Mockito.when(this.userAccountRepository.findUserAccountByEmail(email)).thenReturn(
                Optional.of(new UserAccountDto("ID1", email, "anyPassword"))
        );
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.checkUserAccountExistenceService.checkIfExists(email), ExistentAccountException.class,
                String.format(THERE_IS_ALREADY_AN_ACCOUNT_WITH_EMAIL_S, email)
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
