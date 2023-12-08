package com.vbarrera.backend.service;

import com.vbarrera.backend.model.Movie;
import com.vbarrera.backend.model.error.CustomErrorException;
import com.vbarrera.backend.model.error.ErrorDetails;
import com.vbarrera.backend.model.error.ErrorResponse;
import com.vbarrera.backend.model.error.MovieNotFoundException;
import com.vbarrera.backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Mono<List<Movie>> getAllMoviesRoute() {
        return movieRepository.findAll().collectList();
    }

    public Mono<Movie> getMovieById(UUID id) {
        return movieRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new MovieNotFoundException(id)));
    }

    public Mono<Movie> delete(UUID id) {
        return movieRepository.findById(id)
                .flatMap(movie -> {
                    var mov = movieRepository.deleteById(id).thenReturn(movie);
                    log.info("deleted movie: {} ({})", id, movie.getTitle());
                    return mov;
                }).switchIfEmpty(Mono.error(() -> {
                    var message = String.format("movie not found: %s", id);
                    var errorResponse = ErrorResponse.builder()
                            .traceId(RandomStringUtils.randomAlphanumeric(10))
                            .status(HttpStatus.NOT_FOUND)
                            .timestamp(OffsetDateTime.now())
                            .message(message)
                            .errors(List.of(ErrorDetails.API_MOVIE_NOT_FOUND))
                            .build();
                    throw new CustomErrorException(message, errorResponse);
                }));
    }

    public Mono<Movie> save(Movie movie) {
        movie.setNewObj(true);
        movie.setId(UUID.randomUUID());
        movie.setCreatedAt(LocalDate.now());
        return movieRepository.save(movie);
    }

    public Mono<Movie> update(String id, Movie movie) {
        return movieRepository.findById(UUID.fromString(id))
                .flatMap(mov -> {
                    mov.setNewObj(false);
                    mov.setTitle(movie.getTitle());
                    mov.setDirector(movie.getDirector());
                    mov.setGenre(movie.getGenre());
                    mov.setYear(movie.getYear());
                    mov.setDescription(movie.getDescription());
                    mov.setImage(movie.getImage());
                    mov.setLanguage(movie.getLanguage());
                    mov.setPrices(movie.getPrices());
                    mov.setReleased(movie.getReleased());
                    mov.setReleased(movie.getReleased());
                    mov.setImdbRating(movie.getImdbRating());
                    return movieRepository.save(mov);
                });
    }
}
