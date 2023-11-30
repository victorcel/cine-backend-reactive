package com.vbarrera.backend.repository;

import com.vbarrera.backend.model.Movie;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface MovieRepository extends ReactiveCrudRepository<Movie, UUID> {
}
