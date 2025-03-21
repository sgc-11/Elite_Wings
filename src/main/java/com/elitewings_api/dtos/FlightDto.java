package com.elitewings_api.dtos;

import com.elitewings_api.entities.Flight;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link Flight}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {
    String celebrity;
    String jet;
    @Size(max = 100)
    String departureAirport;
    @Size(max = 100)
    String arrivalAirport;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    Instant departureTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    Instant arrivalTime;
    @Size(max = 200)
    String purpose;
}