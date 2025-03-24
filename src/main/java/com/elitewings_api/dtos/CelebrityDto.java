package com.elitewings_api.dtos;

import com.elitewings_api.entities.Celebrity;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * DTO for {@link Celebrity}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CelebrityDto {
    @Size(max = 100)
    String name;
    @Size(max = 100)
    String profession;
    @Positive
    BigDecimal netWorth;
    Boolean suspiciousActivity;
}