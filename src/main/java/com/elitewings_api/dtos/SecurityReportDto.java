package com.elitewings_api.dtos;

import com.elitewings_api.entities.SecurityReport;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO for {@link SecurityReport}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityReportDto {
    String flight;
    @Size(max = 200)
    String reportedBy;
    @Size(max = 800)
    String description;
    Boolean isResolved;
}