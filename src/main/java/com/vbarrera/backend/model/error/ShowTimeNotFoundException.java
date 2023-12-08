package com.vbarrera.backend.model.error;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ShowTimeNotFoundException extends RuntimeException {

    private final UUID showTimeId;

    private static final String MESSAGE = "showTime not found";

    public ShowTimeNotFoundException(UUID id) {
        super(MESSAGE);
        this.showTimeId = id;
    }
}
