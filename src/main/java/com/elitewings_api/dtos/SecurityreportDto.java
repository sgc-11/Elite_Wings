package com.elitewings_api.dtos;

import com.elitewings_api.entities.Securityreport;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Securityreport}
 */
@Value
public class SecurityreportDto implements Serializable {
    @NotNull
    FlightDto flight;
    @NotNull
    @Size(max = 200)
    String reportedBy;
    @NotNull
    @Size(max = 800)
    String description;
    Boolean isResolved;
}