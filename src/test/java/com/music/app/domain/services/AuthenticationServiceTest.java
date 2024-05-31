package com.music.app.domain.services;

import com.music.app.TestingExceptionAssertions;
import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.entities.UserAccountData;
import com.music.app.domain.exceptions.InvalidCredentialsException;
import com.music.app.domain.ports.UserAccountRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.music.app.domain.services.AuthenticationService.THERE_IS_NO_ACCOUNT_REGISTERED_WITH_EMAIL_S;
import static com.music.app.domain.services.AuthenticationService.THE_PASSWORD_DOES_NOT_MATCH;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthenticationServiceTest {
    @Mock
    private UserAccountRepository userAccountRepository;
    @InjectMocks
    private AuthenticationService authenticationService;

    private AutoCloseable autoCloseable;

    @BeforeAll
    public void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenUserIsNotFoundInRepositoryThrowsException() {
        //Arrange
        String email = "email@email.com";
        String password = "Password]@";
        UserAccountData userAccountData = new UserAccountData(email, password);
        Mockito.when(this.userAccountRepository.findUserAccountByEmail(email)).thenReturn(
                Optional.empty()
        );
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.authenticationService.execute(userAccountData), InvalidCredentialsException.class,
                String.format(THERE_IS_NO_ACCOUNT_REGISTERED_WITH_EMAIL_S, email)
        );
    }

    @Test
    public void whenPasswordDoesntMatchThrowsException() {
        //Arrange
        String email = "email@email.com";
        String password = "Password]@";
        String passwordInDb = "AnotherPass!word";
        UserAccountData userAccountData = new UserAccountData(email, password);
        UserAccountDto userAccountDto = new UserAccountDto(email, passwordInDb);
        Mockito.when(this.userAccountRepository.findUserAccountByEmail(email)).thenReturn(
                Optional.of(userAccountDto)
        );
        //Act-Assert
        TestingExceptionAssertions.assertThrows(
                () -> this.authenticationService.execute(userAccountData), InvalidCredentialsException.class,
                THE_PASSWORD_DOES_NOT_MATCH
        );
    }

    @Test
    public void whenUserAuthenticatesSuccessfullyThenUserInformationIsRetrieved() {
        //Arrange
        String email = "email@email.com";
        String password = "Password]@";
        UserAccountData userAccountData = new UserAccountData(email, password);
        UserAccountDto userAccountDto = new UserAccountDto(email, password);
        Mockito.when(this.userAccountRepository.findUserAccountByEmail(email)).thenReturn(
                Optional.of(userAccountDto)
        );
        //Act
        UserAccountDto userAccountDtoReturned = this.authenticationService.execute(userAccountData);
        //Assert
        Assertions.assertEquals(userAccountDtoReturned.getEmail(), userAccountData.getEmail());
        Assertions.assertEquals(userAccountDtoReturned.getPassword(), userAccountData.getPassword());
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
