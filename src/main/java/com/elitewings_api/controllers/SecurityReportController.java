package com.elitewings_api.controllers;

import com.elitewings_api.dtos.SecurityreportDto;
import com.elitewings_api.services.SecurityReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/security-reports")
public class SecurityReportController {

    private final SecurityReportService securityReportService;

    public SecurityReportController(SecurityReportService securityReportService) {
        this.securityReportService = securityReportService;
    }

    // POST
    @PostMapping
    public ResponseEntity<SecurityreportDto> fileReport(@RequestBody SecurityreportDto dto) {
        SecurityreportDto created = securityReportService.fileReport(dto);
        return ResponseEntity.ok(created);
    }

    // GET
    @GetMapping("/unresolved")
    public ResponseEntity<List<SecurityreportDto>> getUnresolvedReports() {
        List<SecurityreportDto> unresolved = securityReportService.getUnresolvedReports();
        return ResponseEntity.ok(unresolved);
    }

    // PATCH
    @PatchMapping("/{id}/resolve")
    public ResponseEntity<SecurityreportDto> resolveReport(@PathVariable UUID id) {
        SecurityreportDto resolved = securityReportService.resolveReport(id);
        return ResponseEntity.ok(resolved);
    }
}