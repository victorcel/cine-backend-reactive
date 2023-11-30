package com.vbarrera.backend.service;

import com.vbarrera.backend.model.error.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        var errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(MovieNotFoundException ex) {
        var errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND,
                String.format("movie with id %s not found", ex.getMovieId())
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(CustomErrorException.class)
    protected ResponseEntity<ErrorResponse> handleCustomError(RuntimeException ex) {
        var customErrorException = (CustomErrorException) ex;
        return ResponseEntity.status(customErrorException.getErrorResponse().getStatus())
                .body(customErrorException.getErrorResponse());
    }

    @ExceptionHandler(BadCurrencyException.class)
    protected ProblemDetail handleBadCurrencyException(RuntimeException ex) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("invalid currency");
        problemDetail.setProperty("traceId", RandomStringUtils.randomAlphanumeric(10));
        problemDetail.setType(URI.create("http://cedesistemas.edu.co/errors/invalid-currency"));
        problemDetail.setProperty("errors", List.of(ErrorDetails.API_CURRENCY_NOT_SUPPORTED));
        return problemDetail;
    }

    private static ErrorResponse buildErrorResponse(HttpStatus status, String message) {
        return ErrorResponse.builder()
                .traceId(RandomStringUtils.randomAlphanumeric(10))
                .message(message)
                .timestamp(OffsetDateTime.now())
                .status(status)
                .build();
    }
}
