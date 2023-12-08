package com.vbarrera.backend.rest;

import com.github.javafaker.Faker;
import com.vbarrera.backend.Application;
import com.vbarrera.backend.model.Movie;
import com.vbarrera.backend.service.MovieService;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = Application.class)
public class CinemasResourceIntegrationTest {

    private static final Faker FAKER = Faker.instance();

    @Autowired
    private WebTestClient testClient;

    @MockBean
    private MovieService movieService;

    @Test
    void given_movie_id_then_get_movie_by_id() {
        var movie = createDummyMovie();

        given(movieService.getMovieById(movie.getId())).willReturn(Mono.just(movie));

        testClient.get()
                .uri(String.format("/movie/%s", movie.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Movie.class).isEqualTo(movie);
    }

    @Test
    void get_all_movies() {
        var movies = createDummyMovies(4);

        given(movieService.getAllMoviesRoute()).willReturn(Mono.just(movies));

        testClient.get()
                .uri("/movies")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Movie.class).isEqualTo(movies);
    }

    private List<Movie> createDummyMovies(int n) {
        return Stream
                .generate(CinemasResourceIntegrationTest::createDummyMovie)
                .limit(n)
                .collect(Collectors.toList());
    }

    private static Movie createDummyMovie() {
        return Movie.builder()
                .id(UUID.randomUUID())
                .title(FAKER.name().title())
                .director(FAKER.name().fullName())
                .genre("Action")
                .year(BigDecimal.valueOf(RandomUtils.nextInt(1900, 2021)))
                .description(FAKER.lorem().paragraph())
                .image("https://picsum.photos/200/300")
                .language("English")
                .prices(BigDecimal.valueOf(RandomUtils.nextInt(100, 1000)))
                .released("2021-09-01")
                .imdbRating("8.5")
                .createdAt(LocalDate.now())
                .build();
    }

}
