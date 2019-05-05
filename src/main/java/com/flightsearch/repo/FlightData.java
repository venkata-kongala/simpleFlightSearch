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
package com.flightsearch.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightsearch.model.FlightInfo;
import com.flightsearch.model.SearchRequest;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class FlightData {
    private List<FlightInfo> flightInfoList;

    public FlightData() throws IOException {
        String dataStr = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("data.json"));
        List<Map> d = new Gson().fromJson(dataStr, List.class);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss");
        flightInfoList = d.stream().map(e->
            FlightInfo.builder()
                    .aircraft(e.get("aircraft").toString())
                    .departure(LocalDateTime.parse(e.get("departure").toString()))
                    .arrival(LocalDateTime.parse(e.get("arrival").toString()))
                    .carrier(e.get("carrier").toString())
                    .destination(e.get("destination").toString())
                    .origin(e.get("origin").toString())
                    .flightNumber(e.get("flightNumber").toString())
                    .status(e.get("status").toString())
                    .distance(((Double)e.get("distance")).longValue())
                    .travelTime(e.get("travelTime").toString())
                    .build()
        ).collect(Collectors.toList());

    }

    public List<FlightInfo> findBy(SearchRequest searchRequest){
        Stream<FlightInfo> stream = flightInfoList.stream();
        if(Objects.nonNull(searchRequest.getDate())){
            stream = stream.filter(e->e.getDeparture().toLocalDate().equals(searchRequest.getDate()));
        }
        if(searchRequest.getOrigin().isPresent()){
           stream =  stream.filter(e->e.getOrigin().equalsIgnoreCase(searchRequest.getOrigin().get()));
        }
        if(searchRequest.getDestination().isPresent()){
           stream =  stream.filter(e->e.getDestination().equalsIgnoreCase(searchRequest.getDestination().get()));
        }
        if(searchRequest.getFlightNumber().isPresent()){
           stream =  stream.filter(e->e.getFlightNumber().equalsIgnoreCase(searchRequest.getFlightNumber().get()));
        }
        return stream.collect(Collectors.toList());
    }

}
