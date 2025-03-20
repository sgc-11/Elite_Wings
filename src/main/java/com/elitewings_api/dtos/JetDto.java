package com.elitewings_api.dtos;

import com.elitewings_api.entities.Jet;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Jet}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JetDto {
    @Size(max = 150)
    String model;
    Integer capacity;
    String ownerId;
}