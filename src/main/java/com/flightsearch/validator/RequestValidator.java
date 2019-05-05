
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
