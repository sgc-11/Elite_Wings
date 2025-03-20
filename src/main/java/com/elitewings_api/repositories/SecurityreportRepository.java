package com.elitewings_api.repositories;

import com.elitewings_api.entities.Flight;
import com.elitewings_api.entities.Securityreport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SecurityreportRepository extends JpaRepository<Securityreport, UUID> {
    List<Securityreport> findByIsResolvedFalse(boolean isResolved);
}