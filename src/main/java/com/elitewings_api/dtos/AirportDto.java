package com.elitewings_api.dtos;

import com.elitewings_api.entities.Airport;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Airport}
 */
@Value
public class AirportDto {
    @NotNull
    @Size(max = 100)
    String name;
    @NotNull
    @Size(max = 200)
    String location;
    @NotNull
    @Positive
    Integer capacity;
    @Size(max = 200)
    String owners;
}