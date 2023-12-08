package com.vbarrera.backend.service;

import com.vbarrera.backend.model.ShowTime;
import com.vbarrera.backend.model.error.CustomErrorException;
import com.vbarrera.backend.model.error.ErrorDetails;
import com.vbarrera.backend.model.error.ErrorResponse;
import com.vbarrera.backend.model.error.ShowTimeNotFoundException;
import com.vbarrera.backend.repository.ShowTimeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShowTimeService {

    private final ShowTimeRepository showTimeRepository;

    public Mono<List<ShowTime>> getAllShowTimes() {

        return showTimeRepository.findAll().collectList();
    }

    public Mono<ShowTime> getShowTimeById(UUID id) {
        return showTimeRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new ShowTimeNotFoundException(id)));
    }

    public Mono<ShowTime> save(ShowTime showTime) {
        return showTimeRepository.save(showTime);
    }

    public Mono<ShowTime> update(ShowTime showTime, String id ) {
        return showTimeRepository.findById(UUID.fromString(id))
                .flatMap(showTimeRepository::save)
                .switchIfEmpty(Mono.error(() -> new ShowTimeNotFoundException(showTime.getId())));
    }

    public Mono<ShowTime> delete(UUID id) {
        return showTimeRepository.findById(id)
                .flatMap(showTime -> showTimeRepository.deleteById(id).thenReturn(showTime))
                .switchIfEmpty(Mono.error(() -> {
                    var message = String.format("showTime not found: %s", id);
                    var errorResponse = ErrorResponse.builder()
                            .traceId(RandomStringUtils.randomAlphanumeric(10))
                            .status(HttpStatus.NOT_FOUND)
                            .timestamp(OffsetDateTime.now())
                            .message(message)
                            .errors(List.of(ErrorDetails.API_SHOW_TIME_NOT_FOUND))
                            .build();
                    throw new CustomErrorException(message, errorResponse);
                }));
    }

    public Mono<ShowTime> assignMovieToShowTime(ShowTime showTime, UUID movieId) {
        showTime.setNewObj(true);
        showTime.setId(UUID.randomUUID());
        showTime.setMovieId(movieId);
        showTime.setDayOfWeek(showTime.getDayOfWeek());
        showTime.setTime(showTime.getTime());
        showTime.setPrice(showTime.getPrice());
        showTime.setCreatedAt(LocalDate.now());
        return showTimeRepository.save(showTime);
    }
}
