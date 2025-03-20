package com.elitewings_api.dtos;

import com.elitewings_api.entities.Jet;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Jet}
 */
@Value
public class JetDto {
    @NotNull
    @Size(max = 150)
    String model;
    @NotNull
    Integer capacity;
    @NotNull
    CelebrityDto owner;
}