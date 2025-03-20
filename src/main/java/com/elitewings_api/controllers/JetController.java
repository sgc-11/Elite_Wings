package com.elitewings_api.controllers;

import com.elitewings_api.dtos.JetDto;
import com.elitewings_api.services.JetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jets")
public class JetController {

    private final JetService jetService;

    JetController(JetService jetService) {
        this.jetService = jetService;
    }

    @GetMapping
    public List<JetDto> getAllJets() {
        return jetService.getAllJets();
    }

    @GetMapping("/{id}")
    ResponseEntity<JetDto> getJetById(@PathVariable UUID id) {
        JetDto jet =jetService.getJetById(id);
        return ResponseEntity.ok(jet);
    }

    @PostMapping
    ResponseEntity<JetDto> createJet(@RequestBody JetDto jet) {
        JetDto newJet = jetService.createJet(jet);
        return new ResponseEntity<>(newJet, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    ResponseEntity<JetDto> updateJet(@PathVariable UUID id,@RequestBody JetDto jet) {
        JetDto jetUpdated = jetService.updateJet(id, jet);
        return ResponseEntity.ok(jetUpdated);
    }

    @DeleteMapping("{id}")
    ResponseEntity<JetDto> deleteJet(@PathVariable UUID id) {
        jetService.deleteJet(id);

        return ResponseEntity.noContent().build();
    }

}
