package com.elitewings_api.controllers;

import com.elitewings_api.dtos.AirportDto;
import com.elitewings_api.services.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    // GET /api/airports -> Listar todos los aeropuertos
    @GetMapping
    public ResponseEntity<List<AirportDto>> getAllAirports() {
        List<AirportDto> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    // GET /api/airports/{id} -> Obtener detalles de un aeropuerto
    @GetMapping("/{id}")
    public ResponseEntity<AirportDto> getAirportById(@PathVariable UUID id) {
        AirportDto airport = airportService.getAirportById(id);
        return ResponseEntity.ok(airport);
    }
}