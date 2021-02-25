package com.luizalabs.challenge.api;

import com.luizalabs.challenge.application.message.response.ErrorMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ChallengeExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChallengeExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    private ErrorMessageResponse handleExceptions(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ErrorMessageResponse.of(500, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private ErrorMessageResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        LOGGER.error(ex.getMessage(), ex);
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return ErrorMessageResponse.of(400, errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private ErrorMessageResponse handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ErrorMessageResponse.of(400, ex.getCause().getMessage());
    }
}
