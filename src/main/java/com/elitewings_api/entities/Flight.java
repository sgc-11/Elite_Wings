package com.elitewings_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "celebrity_id", nullable = false)
    private Celebrity celebrity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jet_id", nullable = false)
    private Jet jet;

    @Size(max = 100)
    @NotNull
    @Column(name = "departure_airport", nullable = false, length = 100)
    private String departureAirport;

    @Size(max = 100)
    @NotNull
    @Column(name = "arrival_airport", nullable = false, length = 100)
    private String arrivalAirport;

    @NotNull
    @Column(name = "departure_time", nullable = false)
    private Instant departureTime;

    @NotNull
    @Column(name = "arrival_time", nullable = false)
    private Instant arrivalTime;

    @Size(max = 200)
    @Column(name = "purpose", length = 200)
    private String purpose;

}