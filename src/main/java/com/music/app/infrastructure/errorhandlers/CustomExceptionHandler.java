package com.music.app.infrastructure.errorhandlers;

import com.music.app.domain.exceptions.ExistentAccountException;
import com.music.app.domain.exceptions.InvalidCredentialsException;
import com.music.app.domain.exceptions.InvalidValueException;
import com.music.app.domain.exceptions.NullOrEmptyValueExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger(CustomExceptionHandler.class);
    private static final ConcurrentHashMap<String, Integer> CODIGOS_ESTADO = new ConcurrentHashMap<>();
    public static final String SOMETHING_UNKNOWN_HAPPENED_PLEASE_CONTACT_THE_ADMIN = "Something unknown happened. Please contact the admin";

    public CustomExceptionHandler() {
        CODIGOS_ESTADO.put(ExistentAccountException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(InvalidValueException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(NullOrEmptyValueExeption.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(InvalidCredentialsException.class.getSimpleName(), HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleAllExceptions(Exception exception) {
        ResponseEntity<Error> result;
        String exceptionName = exception.getClass().getSimpleName();
        String message = exception.getMessage();
        Integer code = CODIGOS_ESTADO.get(exceptionName);
        if (code != null) {
            Error error = new Error(exceptionName, message);
            result = new ResponseEntity<>(error, HttpStatus.valueOf(code));
        } else {
            LOGGER_ERROR.error(exceptionName, exception);
            Error error = new Error(exceptionName, SOMETHING_UNKNOWN_HAPPENED_PLEASE_CONTACT_THE_ADMIN);
            result = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
