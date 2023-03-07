package com.epam.esm.web.exception;

import com.epam.esm.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
    private static final List<String> AVAILABLE_LOCALES = Arrays.asList("en_US", "ua_UA");
    private static final Locale DEFAULT_LOCALE = new Locale("en", "US");

    private final MessageConfig messageSource;

    public RestResponseEntityExceptionHandler(MessageConfig messageConfig) {
        this.messageSource = messageConfig;
    }


    private ResponseEntity<ExceptionResponse> buildErrorResponse(String message, int code,
                                                                 HttpStatus status) {
        ExceptionResponse response = new ExceptionResponse(message, code);
        return new ResponseEntity<>(response, status);
    }

    private String resolveResourceBundle(String key, Locale locale) {
        if (!AVAILABLE_LOCALES.contains(locale.toString())) {
            locale = DEFAULT_LOCALE;
        }
        return messageSource.messageSource().getMessage(key, null, locale);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(
            EntityNotFoundException e, Locale locale) {
        return buildErrorResponse(resolveResourceBundle(e.getMessage(), locale),
                40002, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidDataException(
            InvalidDataException e, Locale locale) {
        return buildErrorResponse(resolveResourceBundle(e.getMessage(), locale),
                42201, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateEntityException(
            DuplicateEntityException e, Locale locale) {
        return buildErrorResponse(resolveResourceBundle(e.getMessage(), locale),
                40901, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateEntityException(
            InvalidParameterException e, Locale locale) {
        return buildErrorResponse(resolveResourceBundle(e.getMessage(), locale),
                42301, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleOtherExceptions(Exception e) {
        return buildErrorResponse(e.getMessage(), 50000, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidJwtException(Locale locale) {
        return buildErrorResponse(resolveResourceBundle("jwt.invalid", locale),
                40100, HttpStatus.UNAUTHORIZED);
    }
    public ExceptionResponse buildNoJwtResponseObject(Locale locale) {
        return new ExceptionResponse(resolveResourceBundle("jwt.not.exist", locale), 40101);
    }
}
