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
package com.flightsearch.service;

import com.flightsearch.model.FlightInfo;
import com.flightsearch.model.SearchRequest;
import com.flightsearch.repo.FlightData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private FlightData data;

    public List<FlightInfo> find(SearchRequest request){
        return data.findBy(request);
    }
}
