package com.elitewings_api.controllers;

import com.elitewings_api.dtos.CelebrityDto;
import com.elitewings_api.services.CelebrityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/celebrities")
 class CelebrityController {

    private final CelebrityService celebrityService;

    CelebrityController(CelebrityService celebrityService) {
        this.celebrityService = celebrityService;
    }

    @GetMapping
    List<CelebrityDto> getCelebrities() {
        return celebrityService.getAllCelebrities();
    }

    @GetMapping("/{id}")
    ResponseEntity<CelebrityDto> getCelebrityById(@PathVariable UUID id) {
        CelebrityDto celebrity = celebrityService.getCelebrityById(id);
        return ResponseEntity.ok(celebrity);
    }

    @PostMapping
    ResponseEntity<CelebrityDto> createCelebrity(@RequestBody CelebrityDto celebrityDto) {
        CelebrityDto createdCelebrity = celebrityService.createCelebrity(celebrityDto);
        return new ResponseEntity<>(createdCelebrity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<CelebrityDto> updateCelebrity(@PathVariable UUID id, @RequestBody CelebrityDto celebrityDto) {
        CelebrityDto updatedCelebrity = celebrityService.updateCelebrity(id, celebrityDto);
        return ResponseEntity.ok(updatedCelebrity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCelebrity(@PathVariable UUID id) {
        celebrityService.deleteCelebrity(id);
        return ResponseEntity.noContent().build();
    }
}

