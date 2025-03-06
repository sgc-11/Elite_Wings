package com.elitewings_api.repositories;

import com.elitewings_api.entities.Celebrity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CelebrityRepository extends JpaRepository<Celebrity, UUID> {
    Object findCelebrityById(UUID id);
}