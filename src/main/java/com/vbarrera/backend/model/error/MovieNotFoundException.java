package com.vbarrera.backend.model.error;

import lombok.Getter;

import java.util.UUID;

@Getter
public class MovieNotFoundException extends RuntimeException {

    private final UUID movieId;

    private static final String MESSAGE = "movie not found";
    public MovieNotFoundException(UUID id) {
        super(MESSAGE);
        this.movieId = id;
    }
}
