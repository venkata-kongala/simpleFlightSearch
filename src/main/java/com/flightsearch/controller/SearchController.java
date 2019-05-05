package com.flightsearch.controller;

import com.flightsearch.model.FlightInfo;
import com.flightsearch.model.SearchRequest;
import com.flightsearch.service.SearchService;
import com.flightsearch.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private RequestValidator validator;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<FlightInfo> search(@RequestParam("date") String date,
                                   @RequestParam(required = false, value = "origin") String origin,
                                   @RequestParam (required = false, value = "destination") String destination,
                                   @RequestParam(required = false, value = "flightNumber") String flightNumber){
        final SearchRequest request = SearchRequest.builder()
                .date(LocalDate.parse(date))
                .destination(Optional.ofNullable(destination))
                .origin(Optional.ofNullable(origin))
                .flightNumber(Optional.ofNullable(flightNumber))
                .build();
            validator.validate(request);
        return searchService.find(request);

    }

}
