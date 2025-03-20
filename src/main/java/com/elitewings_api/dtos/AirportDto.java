package com.elitewings_api.dtos;

import com.elitewings_api.entities.Airport;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Airport}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportDto {
    @Size(max = 100)
    String name;
    @Size(max = 200)
    String location;
    Integer capacity;
    @Size(max = 200)
    String owners;
}