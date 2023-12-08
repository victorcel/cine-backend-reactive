package com.vbarrera.backend.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class RatingModel {
    private String Source;
    private String Value;
}
