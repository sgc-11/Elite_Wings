package com.elitewings_api.repositories;

import com.elitewings_api.entities.Jet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JetRepository extends JpaRepository<Jet, UUID> {
}