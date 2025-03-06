package com.elitewings_api.dtos;

import com.elitewings_api.entities.Celebrity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Celebrity}
 */
@Value
public class CelebrityDto implements Serializable {
    @NotNull
    @Size(max = 100)
    String name;
    @NotNull
    @Size(max = 100)
    String profession;
    @NotNull
    @Positive
    BigDecimal netWorth;
    @NotNull
    Boolean suspiciousActivity;
}