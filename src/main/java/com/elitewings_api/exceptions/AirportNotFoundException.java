package com.elitewings_api.exceptions;

public class AirportNotFoundException  extends RuntimeException{
    public AirportNotFoundException(String message){
        super(message);
    }
}
