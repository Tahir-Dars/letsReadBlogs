package com.letsreadhere.blog.controller;

import com.letsreadhere.blog.domain.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception) {
        log.error("The Exception is ", exception);
        ApiErrorResponse response = ApiErrorResponse.builder().
                status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An Unexpected error has occurred").build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException argumentException) {
        log.error("IllegalArgumentException", argumentException);
        ApiErrorResponse response = ApiErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("The values you sent are invalid")
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}


