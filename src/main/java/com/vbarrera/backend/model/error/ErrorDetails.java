package com.vbarrera.backend.model.error;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonSerialize(using = ErrorDetailsSerializer.class)
@AllArgsConstructor
@Getter
public enum ErrorDetails {
    API_MOVIE_NOT_FOUND(124, "movie not found", "http://www.omdbapi.com/?apikey=[yourkey]&i=12345"),
    API_SHOW_TIME_NOT_FOUND(124, "showTime not found", "http://www.omdbapi.com/?apikey=[yourkey]&i=12345"),
    API_CURRENCY_NOT_SUPPORTED(125, "currency not supported", "http://www.omdbapi.com/?apikey=[yourkey]&i=12345");
    private final Integer errorCode;
    private final String errorMessage;
    private final String referenceUrl;
}
