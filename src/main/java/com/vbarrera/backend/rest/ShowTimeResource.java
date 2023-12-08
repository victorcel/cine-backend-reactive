package com.vbarrera.backend.rest;


import com.vbarrera.backend.model.ShowTime;
import com.vbarrera.backend.service.ShowTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("showtime")
public class ShowTimeResource {

    private final ShowTimeService showTimeService;

    @GetMapping
    public Mono<ResponseEntity<List<ShowTime>>> getAllShowTimes(){
        return showTimeService
                .getAllShowTimes()
                .map(showTime -> new ResponseEntity<>(showTime, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public Mono<ResponseEntity<ShowTime>> getShowTimeById(@PathVariable String id){
        return showTimeService
                .getShowTimeById(UUID.fromString(id))
                .map(showTime -> new ResponseEntity<>(showTime, HttpStatus.OK));
    }

}
