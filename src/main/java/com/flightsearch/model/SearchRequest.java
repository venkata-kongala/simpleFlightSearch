/*
 * InstaReM Overseas Money Transfer.
 * https://www.instarem.com/en-in/
 *
 * Copyright (c) 2019 InstaReM
 *
 * This file is licensed and cannot be accessed outside InstaReM.
 * It can only be accessed and modified by the authorized InstaReM Technical Teams.
 * All unauthorized modifications to the content of this file,
 * will be prosecuted under the Copyright Act.
 *
 */
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
