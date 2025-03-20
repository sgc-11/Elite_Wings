package com.elitewings_api.dtos;

import com.elitewings_api.entities.Securityreport;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Securityreport}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityreportDto {
    FlightDto flight;
    @Size(max = 200)
    String reportedBy;
    @Size(max = 800)
    String description;
    Boolean isResolved;
}