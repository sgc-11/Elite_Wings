package com.elitewings_api.repositories;

import com.elitewings_api.entities.SecurityReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SecurityreportRepository extends JpaRepository<SecurityReport, UUID> {
}