package com.music.app.domain.services;

import com.music.app.TestingExceptionAssertions;
import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.exceptions.InvalidCredentialsException;
import com.music.app.domain.model.UserAccountData;
import com.music.app.domain.services.user.AuthenticationService;
import com.music.app.infrastructure.services.FindUserService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.music.app.domain.services.user.AuthenticationService.THE_PASSWORD_DOES_NOT_MATCH;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthenticationServiceTest {
    @Mock
    private FindUserService findUserService;
    @InjectMocks
    private AuthenticationService authenticationService;

    private AutoCloseable autoCloseable;

    @BeforeAll
    public void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }


    @Test
    public void whenPasswordDoesntMatchThrowsException() {
        //Arrange
        String email = "email@email.com";
        String password = "Password]@";
        String passwordInDb = "AnotherPass!word";
        UserAccountData userAccountData = new UserAccountData(email, password);
        UserAccountDto userAccountDto = new UserAccountDto("ID1", email, passwordInDb);
        Mockito.when(this.findUserService.findUser(email)).thenReturn(userAccountDto);
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
        UserAccountDto userAccountDto = new UserAccountDto("ID2", email, password);
        Mockito.when(this.findUserService.findUser(email)).thenReturn(userAccountDto);
        //Act
        UserAccountDto userAccountDtoReturned = this.authenticationService.execute(userAccountData);
        //Assert
        Assertions.assertEquals(userAccountDtoReturned.email(), userAccountData.email());
        Assertions.assertEquals(userAccountDtoReturned.password(), userAccountData.password());
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
