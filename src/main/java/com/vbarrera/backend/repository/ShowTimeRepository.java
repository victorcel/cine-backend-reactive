package com.vbarrera.backend.repository;

import com.vbarrera.backend.model.ShowTime;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface ShowTimeRepository extends ReactiveCrudRepository<ShowTime, UUID> {
}
