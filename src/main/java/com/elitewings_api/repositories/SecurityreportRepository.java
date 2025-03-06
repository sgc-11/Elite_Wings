package com.elitewings_api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SecurityreportRepository extends JpaRepository<Securityreport, UUID> {
}