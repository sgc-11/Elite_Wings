package com.elitewings_api.dtos;

import com.elitewings_api.entities.Flight;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Flight}
 */
@Value
public class FlightDto {
    @NotNull
    CelebrityDto celebrity;
    @NotNull
    JetDto jet;
    @NotNull
    @Size(max = 100)
    String departureAirport;
    @NotNull
    @Size(max = 100)
    String arrivalAirport;
    @NotNull
    Instant departureTime;
    @NotNull
    Instant arrivalTime;
    @NotNull
    @Size(max = 200)
    String purpose;
}