package com.vbarrera.backend.service;

import com.vbarrera.backend.model.ShowTime;
import com.vbarrera.backend.repository.ShowTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
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
        return showTimeRepository.findById(id);
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
