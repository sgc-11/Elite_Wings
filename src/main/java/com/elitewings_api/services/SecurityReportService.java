package com.elitewings_api.services;

import com.elitewings_api.dtos.SecurityReportDto;
import com.elitewings_api.entities.SecurityReport;
import com.elitewings_api.exceptions.SecurityReportNotFoundException;
import com.elitewings_api.repositories.FlightRepository;
import com.elitewings_api.repositories.SecurityreportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SecurityReportService {

    private final SecurityreportRepository securityReportRepository;
    private final FlightRepository flightRepository;

    @Autowired
    public SecurityReportService(SecurityreportRepository securityReportRepository,
                                 FlightRepository flightRepository) {
        this.securityReportRepository = securityReportRepository;
        this.flightRepository = flightRepository;
    }

    // POST
    public SecurityReportDto fileReport(SecurityReportDto dto) {
        SecurityReport newreport = new SecurityReport();
        newreport.setId(UUID.randomUUID());

        newreport.setFlight(flightRepository.findFlightById(UUID.fromString(dto.getFlight())));
        newreport.setReportedBy(dto.getReportedBy());
        newreport.setDescription(dto.getDescription());
        newreport.setIsResolved(false);

        SecurityReport saved = securityReportRepository.save(newreport);
        return convertToDto(saved);
    }

    // GET
    public List<SecurityReportDto> getUnresolvedReports() {
        List<SecurityReport> unresolved = securityReportRepository.findAll().stream().filter(res -> !res.getIsResolved()).toList();

        return unresolved.stream()
                .map(this::convertToDto)
                .toList();
    }

    // PATCH
    public SecurityReportDto resolveReport(UUID id, SecurityReportDto updateReport) {
        SecurityReport report = securityReportRepository.findById(id)
                .orElseThrow(() -> new SecurityReportNotFoundException("Report"+ id +"not found"));

        report.setIsResolved(true);
        SecurityReport updated = securityReportRepository.save(report);
        return convertToDto(updated);
    }

    private SecurityReportDto convertToDto(SecurityReport report) {
        SecurityReportDto dto = new SecurityReportDto();
        dto.setFlight(report.getFlight().getId().toString());
        dto.setReportedBy(report.getReportedBy());
        dto.setDescription(report.getDescription());
        dto.setIsResolved(report.getIsResolved());
        return dto;
    }
}
