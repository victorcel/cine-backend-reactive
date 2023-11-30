package com.vbarrera.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table("movies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Movie implements Persistable<UUID> {

    @Id
    @JsonProperty("id")
    private UUID id;

    @NotNull
    private String title;

    @NotNull
    private String director;

    @NotNull
    private String genre;

    @NotNull
    private String year;

    @NotNull
    private String description;

    @NotNull
    private String image;

    @NotNull
    private String language;

    @NotNull
    private String prices;

    @NotNull
    private String released;

    @NotNull
    private String imdbRating;

    @NotNull
    private LocalDate createdAt;

    @Transient
    @JsonIgnore
    private boolean newObj;

    @Override
    @JsonIgnore
    @Transient
    public boolean isNew() {
        return this.newObj || id == null;
    }

}
