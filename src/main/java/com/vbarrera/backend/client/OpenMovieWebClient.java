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
    private static String key;

    private static final String CURRENCY_URL = String.format("http://www.omdbapi.com/?apikey=%s&i=",key);

    private static final WebClient webClient;

    static {
        webClient = WebClient.create(CURRENCY_URL);
    }


    @Override
    public OpenMovieModel getMovie(String title) {
        return webClient
                .get()
                .uri(title)
                .exchangeToMono(OpenMovieWebClient::handleResponse)
                .share()
                .block();
    }

    private static Mono<OpenMovieModel> handleResponse(ClientResponse resp) {
        if (resp.statusCode().equals(HttpStatus.OK)) {
            return resp.bodyToMono(OpenMovieModel.class);
        } else {
            return resp.createException().flatMap(Mono::error);
        }
    }
}
