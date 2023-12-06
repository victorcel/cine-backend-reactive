package com.vbarrera.backend.config;

import com.vbarrera.backend.model.Movie;
import com.vbarrera.backend.model.ShowTime;
import com.vbarrera.backend.model.error.MovieNotFoundException;
import com.vbarrera.backend.service.MovieService;
import com.vbarrera.backend.service.ShowTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@RequiredArgsConstructor
public class FunctionalConfig {
    private static final String MOVIE_ID = "/movie/{id}";
    private final MovieService movieService;
    private final ShowTimeService showTimeService;

    @Bean
    RouterFunction<ServerResponse> getAllMoviesRoute() {
        return route(GET("/movies"),
                req -> ok().body(movieService.getAllMoviesRoute(), Movie.class));
    }

    @Bean
    RouterFunction<ServerResponse> getMovieByIdRoute() {
        return route(GET(MOVIE_ID),
                req -> movieService.getMovieById(UUID.fromString(req.pathVariable("id")))
                        .flatMap(movie -> ok().body(Mono.just(movie), Movie.class))
                        .switchIfEmpty(ServerResponse.from(
                                ErrorResponse.builder(new MovieNotFoundException(
                                                UUID.fromString(req.pathVariable("id"))), HttpStatus.NOT_FOUND, "department not found")
                                        .type(URI.create("http://www.omdbapi.com/?apikey=[yourkey]&i=12345"))
                                        .title("movie not found")
                                        .build())
                        )
        );
    }

    @Bean
    RouterFunction<ServerResponse> deleteMovie() {
        return route(DELETE(MOVIE_ID),
                req -> ok().body(movieService.delete(UUID.fromString(req.pathVariable("id"))), Movie.class));
    }

    @Bean
    RouterFunction<ServerResponse> updateMovie() {
        return route(PUT(MOVIE_ID),
                req -> req.body(BodyExtractors.toMono(Movie.class))
                        .flatMap(employee -> movieService.update(req.pathVariable("id"), employee))
                        .flatMap(e -> ok().body(BodyInserters.fromValue(e)))
        );
    }

    @Bean
    RouterFunction<ServerResponse> assignMovieToShowTime() {
        return route(POST("/showtime/movie/{movieId}"),
                req -> req.body(BodyExtractors.toMono(ShowTime.class))
                        .flatMap(showtime -> showTimeService.assignMovieToShowTime(
                                showtime, UUID.fromString(req.pathVariable("employeeId")))
                        )
                        .flatMap(e -> ok().body(BodyInserters.fromValue(e)))
        );
    }


}
