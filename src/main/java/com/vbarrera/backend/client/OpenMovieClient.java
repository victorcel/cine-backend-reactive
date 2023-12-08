package com.vbarrera.backend.client;

import reactor.core.publisher.Mono;

public interface OpenMovieClient {
    Mono<OpenMovieModel> getMovie(String title);
}
