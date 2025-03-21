package com.elitewings_api.controllers;

import com.elitewings_api.dtos.SecurityReportDto;
import com.elitewings_api.services.SecurityReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/security-reports")
public class SecurityReportController {

    private final SecurityReportService securityReportService;
    //Constructor
    public SecurityReportController(SecurityReportService securityReportService) {
        this.securityReportService = securityReportService;
    }

    // GET
    @GetMapping("/unresolved")
    public ResponseEntity<List<SecurityReportDto>> getUnresolvedReports() {
        List<SecurityReportDto> unresolved = securityReportService.getUnresolvedReports();
        return ResponseEntity.ok(unresolved);
    }

    // POST
    @PostMapping
    public ResponseEntity<SecurityReportDto> fileReport(@RequestBody SecurityReportDto dto) {
        SecurityReportDto created = securityReportService.fileReport(dto);
        return ResponseEntity.ok(created);
    }

    // PATCH
    @PatchMapping("/{id}/resolve")
    public ResponseEntity<SecurityReportDto> resolveReport(@PathVariable UUID id, @RequestBody SecurityReportDto dto) {
        SecurityReportDto resolved = securityReportService.resolveReport(id, dto);
        return ResponseEntity.ok(resolved);
    }
}