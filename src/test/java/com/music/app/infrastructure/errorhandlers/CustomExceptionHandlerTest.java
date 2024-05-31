package com.music.app.infrastructure.errorhandlers;

import com.music.app.domain.exceptions.ExistentAccountException;
import com.music.app.domain.exceptions.InvalidCredentialsException;
import com.music.app.domain.exceptions.InvalidValueException;
import com.music.app.domain.exceptions.NullOrEmptyValueExeption;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomExceptionHandlerTest {

    @Test
    public void testHandleExistentAccountException() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        ExistentAccountException ex = new ExistentAccountException("Account already exists");

        ResponseEntity<Error> response = customExceptionHandler.handleAllExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ExistentAccountException", response.getBody().getExceptionName());
        assertEquals("Account already exists", response.getBody().getMessage());
    }

    @Test
    public void testHandleInvalidValueException() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        InvalidValueException ex = new InvalidValueException("Invalid value");

        ResponseEntity<Error> response = customExceptionHandler.handleAllExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("InvalidValueException", response.getBody().getExceptionName());
        assertEquals("Invalid value", response.getBody().getMessage());
    }

    @Test
    public void testHandleNullOrEmptyValueException() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        NullOrEmptyValueExeption ex = new NullOrEmptyValueExeption("Value cannot be null or empty");

        ResponseEntity<Error> response = customExceptionHandler.handleAllExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("NullOrEmptyValueExeption", response.getBody().getExceptionName());
        assertEquals("Value cannot be null or empty", response.getBody().getMessage());
    }

    @Test
    public void testHandleInvalidCredentialsException() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        InvalidCredentialsException ex = new InvalidCredentialsException("Invalid credentials");

        ResponseEntity<Error> response = customExceptionHandler.handleAllExceptions(ex);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("InvalidCredentialsException", response.getBody().getExceptionName());
        assertEquals("Invalid credentials", response.getBody().getMessage());
    }

    @Test
    public void testHandleUnknownException() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        Exception ex = new Exception("Unknown exception");

        ResponseEntity<Error> response = customExceptionHandler.handleAllExceptions(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Exception", response.getBody().getExceptionName());
        assertEquals("Something unknown happened. Please contact the admin", response.getBody().getMessage());
    }
}
