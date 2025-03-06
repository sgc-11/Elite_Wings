package com.elitewings_api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CelebrityRepository extends JpaRepository<Celebrity, UUID> {
}