package com.vbarrera.backend.rest.v2;

import com.vbarrera.backend.client.OpenMovieModel;
import com.vbarrera.backend.client.OpenMovieWebClient;
import com.vbarrera.backend.model.ShowTime;
import com.vbarrera.backend.service.ShowTimeService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("v2/showtime")
public class ShowTimeResourceV2 {

    private final ShowTimeService showTimeService;
    private final OpenMovieWebClient openMovieWebClient;

    @GetMapping(path = "/{id}")
    public Mono<ResponseEntity<OpenMovieModel>> getShowOpenMovieById(@NotNull @PathVariable String id) {
        return openMovieWebClient.getMovie(id)
                .map(movie -> new ResponseEntity<>(
                        movie, movie.getResponse().equals("True") ?
                        HttpStatus.OK :
                        HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public Mono<ShowTime> deleteShowTimeById(@NotNull @PathVariable String id) {
        return showTimeService.delete(UUID.fromString(id));
    }

}
