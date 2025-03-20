package com.elitewings_api.services;

import com.elitewings_api.dtos.SecurityreportDto;
import com.elitewings_api.entities.Flight;
import com.elitewings_api.entities.Securityreport;
import com.elitewings_api.exceptions.FlightNotFoundException;
import com.elitewings_api.exceptions.SecurityReportNotFoundException;
import com.elitewings_api.repositories.FlightRepository;
import com.elitewings_api.repositories.SecurityreportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SecurityReportService {

    private final SecurityreportRepository securityReportRepository;
    private final FlightRepository flightRepository;

    public SecurityReportService(SecurityreportRepository securityReportRepository,
                                 FlightRepository flightRepository) {
        this.securityReportRepository = securityReportRepository;
        this.flightRepository = flightRepository;
    }

    // POST
    public SecurityreportDto fileReport(SecurityreportDto dto) {
        Securityreport report = new Securityreport();
        report.setId(UUID.randomUUID());

        Flight flight = flightRepository.findById(UUID.fromString(String.valueOf(dto.getFlight())))
                .orElseThrow(()-> new FlightNotFoundException("Flight not found"));
        report.setFlight(flight);

        report.setReportedBy(dto.getReportedBy());
        report.setDescription(dto.getDescription());
        report.setIsResolved(false);

        Securityreport saved = securityReportRepository.save(report);
        return convertToDto(saved);
    }

    // GET
    public List<SecurityreportDto> getUnresolvedReports() {
        List<Securityreport> unresolved = securityReportRepository.findByIsResolvedFalse(true);
        return unresolved.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // PATCH
    public SecurityreportDto resolveReport(UUID id) {
        Securityreport report = securityReportRepository.findById(id)
                .orElseThrow(() -> new SecurityReportNotFoundException("Report not found"));
        report.setIsResolved(true);

        Securityreport updated = securityReportRepository.save(report);
        return convertToDto(updated);
    }

    private SecurityreportDto convertToDto(Securityreport report) {
        SecurityreportDto dto = new SecurityreportDto();
        dto.setReportedBy(report.getReportedBy());
        dto.setDescription(report.getDescription());
        dto.setIsResolved(report.getIsResolved());
        return dto;
    }
}
