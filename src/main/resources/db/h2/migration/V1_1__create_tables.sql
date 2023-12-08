CREATE TABLE movies
(
    id          uuid PRIMARY KEY,
    title       varchar(100)   not null,
    director    varchar(100)  not null,
    genre       varchar(100)  not null,
    release_year        DECIMAL(4, 0)  not null,
    description varchar(255)   not null,
    image       varchar(255)   not null,
    language    varchar(100)  not null,
    prices      DECIMAL(10, 2) not null,
    released    varchar(100)  not null,
    imdb_rating  varchar(100)  not null,
    created_at  DATE           not null
);

CREATE TABLE showtime
(
    id        uuid         NOT NULL PRIMARY KEY,
    movie_id   uuid         NOT NULL,
    day_of_week varchar(100) NOT NULL,
    showtime  varchar(100) NOT NULL,
    price     int          NOT NULL,
    created_at timestamp    NOT NULL
);

ALTER TABLE showtime ADD FOREIGN KEY (movie_id) REFERENCES movies (id) ON DELETE CASCADE;
