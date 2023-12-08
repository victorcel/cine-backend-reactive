package com.vbarrera.backend.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OpenMovieWebClient implements OpenMovieClient{

    @Value("${credentials.key-movies}")
    private String key;

    private static final String HOST_URL = "https://www.omdbapi.com";

    private static final WebClient webClient;

    static {
        webClient = WebClient.create(HOST_URL);
    }


    @Override
    public Mono<OpenMovieModel> getMovie(String imdbID) {
        return webClient
                .get()
                .uri(String.format("/?apikey=%s&i=%s", key, imdbID))
                .exchangeToMono(OpenMovieWebClient::handleResponse);
    }

    private static Mono<OpenMovieModel> handleResponse(ClientResponse resp) {
        if (resp.statusCode().equals(HttpStatus.OK)) {
            return resp.bodyToMono(OpenMovieModel.class);
        } else {
            return resp.createException().flatMap(Mono::error);
        }
    }
}
