package com.flightsearch.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FlightInfo {
    private String flightNumber;
    private String carrier;
    private String origin;
    private LocalDateTime departure;
    private String destination;
    private LocalDateTime arrival;
    private String aircraft;
    private Long distance;
    private String travelTime;
    private String status;



}
