package com.elitewings_api.repositories;

import com.elitewings_api.entities.Celebrity;
import com.elitewings_api.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {
    List<Flight> findByPurpose(String purpose);
}