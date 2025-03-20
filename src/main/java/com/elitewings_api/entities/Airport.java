package com.elitewings_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "airports")
public class Airport {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 200)
    @NotNull
    @Column(name = "location", nullable = false, length = 200)
    private String location;

    @NotNull
    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @ColumnDefault("'Simon and Paulina the best owners'")
    @Column(name = "owners", length = 200)
    private String owners;

}