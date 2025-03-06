package com.elitewings_api;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "celebrities")
public class Celebrity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 100)
    @NotNull
    @Column(name = "profession", nullable = false, length = 100)
    private String profession;

    @Column(name = "net_worth", precision = 15, scale = 2)
    private BigDecimal netWorth;

    @ColumnDefault("false")
    @Column(name = "suspicious_activity")
    private Boolean suspiciousActivity;

}