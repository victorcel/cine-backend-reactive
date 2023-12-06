package com.vbarrera.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Table("showtime")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ShowTime implements Persistable<UUID> {

    @Id
    @JsonProperty("id")
    private UUID id;

    @NotNull
    private UUID movieId;

    @NotNull
    private String dayOfWeek;

    @NotNull
    private String time;

    @NotNull
    private BigDecimal price;

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
