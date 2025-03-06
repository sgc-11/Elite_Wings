package com.elitewings_api;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "securityreports")
public class Securityreport {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Size(max = 200)
    @NotNull
    @Column(name = "reported_by", nullable = false, length = 200)
    private String reportedBy;

    @Size(max = 800)
    @NotNull
    @Column(name = "description", nullable = false, length = 800)
    private String description;

    @ColumnDefault("false")
    @Column(name = "is_resolved")
    private Boolean isResolved;

}