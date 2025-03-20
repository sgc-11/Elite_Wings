package com.elitewings_api.dtos;

import com.elitewings_api.entities.Flight;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Flight}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {
    CelebrityDto celebrity;
    JetDto jet;
    @Size(max = 100)
    String departureAirport;
    @Size(max = 100)
    String arrivalAirport;
    Instant departureTime;
    Instant arrivalTime;
    @Size(max = 200)
    String purpose;
}