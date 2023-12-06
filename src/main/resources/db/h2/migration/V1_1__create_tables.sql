CREATE TABLE movies
(
    id          uuid          NOT NULL PRIMARY KEY,
    title       varchar(100)  not null,
    director    varchar(100)  not null,
    genre       varchar(100)  not null,
    year        int           not null,
    description varchar(1000) not null,
    image       varchar(1000) not null,
    language    varchar(100)  not null,
    prices      int           not null,
    released    varchar(100)  not null,
    imdb_rating  varchar(100)  not null,
    createdAt   timestamp     not null,
);

CREATE TABLE showtime
(
    id        uuid         NOT NULL PRIMARY KEY,
    movie_id   uuid         NOT NULL,
    day_of_week varchar(100) NOT NULL,
    showtime  varchar(100) NOT NULL,
    price     int          NOT NULL,
    created_at timestamp    NOT NULL,
)

ALTER TABLE showtime ADD PRIMARY KEY (id);
ALTER TABLE showtime ADD FOREIGN KEY (movie_id) REFERENCES movies (id) ON DELETE CASCADE;
