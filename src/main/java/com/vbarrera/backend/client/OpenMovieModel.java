package com.vbarrera.backend.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenMovieModel {
    @JsonProperty("Title")
    private String Title;

    @JsonProperty("Year")
    private String Year;

    @JsonProperty("Rated")
    private String Rated;

    @JsonProperty("Released")
    private String Released;

    @JsonProperty("Runtime")
    private String Runtime;

    @JsonProperty("Genre")
    private String Genre;

    @JsonProperty("Director")
    private String Director;

    @JsonProperty("Writer")
    private String Writer;

    @JsonProperty("Actors")
    private String Actors;

    @JsonProperty("Plot")
    private String Plot;

    @JsonProperty("Language")
    private String Language;

    @JsonProperty("Country")
    private String Country;

    @JsonProperty("Awards")
    private String Awards;

    @JsonProperty("Poster")
    private String Poster;

    @JsonProperty("Ratings")
    private List<RatingModel> Ratings;

    @JsonProperty("Metascore")
    private String Metascore;

    @JsonProperty("imdbRating")
    private String imdbRating;

    @JsonProperty("imdbVotes")
    private String imdbVotes;

    @JsonProperty("imdbID")
    private String imdbID;

    @JsonProperty("Type")
    private String Type;

    @JsonProperty("DVD")
    private String DVD;

    @JsonProperty("BoxOffice")
    private String BoxOffice;

    @JsonProperty("Production")
    private String Production;

    @JsonProperty("Website")
    private String Website;

    @JsonProperty("Response")
    private String Response;

}
