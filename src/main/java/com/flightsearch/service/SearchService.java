
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
