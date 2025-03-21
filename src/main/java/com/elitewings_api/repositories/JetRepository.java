package com.elitewings_api.repositories;

import com.elitewings_api.entities.Jet;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JetRepository extends JpaRepository<Jet, UUID> {
    Jet findByModel(String model);

    @NotNull Jet findJetById(UUID id);
}