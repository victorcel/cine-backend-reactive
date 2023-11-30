package com.vbarrera.backend.service;

import com.vbarrera.backend.model.ShowTime;
import com.vbarrera.backend.repository.ShowTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShowTimeService {

    private final ShowTimeRepository showTimeRepository;

    public Flux<ShowTime> getAllShowTimes() {
        return showTimeRepository.findAll();
    }

    public Mono<ShowTime> getShowTimeById(UUID id) {
        return showTimeRepository.findById(id);
    }

    public Mono<ShowTime> createShowTime(ShowTime showTime, UUID movieId) {
        showTime.setNewObj(true);
        showTime.setMovieId(movieId);
        showTime.setCreatedAt(LocalDate.now());
        return showTimeRepository.save(showTime);
    }
}
