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
package com.flightsearch.validator;

import com.flightsearch.model.SearchRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RequestValidator {

    public void validate(SearchRequest request){
        if(Objects.isNull(request.getDate())){
            throw new RuntimeException("Invalid Input. Date is mandatory param");
        }
        if(request.getFlightNumber().isPresent()){
            if(request.getOrigin().isPresent() || request.getDestination().isPresent()){
                throw new RuntimeException("Invalid Input. Origin destiation and flight number both cannot be searched");
            }

        }else if(request.getDestination().isPresent() && request.getOrigin().isPresent()){
                if(request.getFlightNumber().isPresent()){
                    throw new RuntimeException("Invalid input");
                }
        }else {
            throw new RuntimeException("Invalid Inputs..");
        }
    }
}
