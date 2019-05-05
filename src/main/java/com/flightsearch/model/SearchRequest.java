
package com.flightsearch.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Optional;

@Data
@Builder
public class SearchRequest {

    private Optional<String> origin;
    private Optional<String> destination;
    private Optional<String> flightNumber;
    private LocalDate date;
}
