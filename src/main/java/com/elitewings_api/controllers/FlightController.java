package com.elitewings_api.controllers;

import com.elitewings_api.dtos.FlightDto;
import com.elitewings_api.services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    //Get All
    @GetMapping()
    public List<FlightDto> getAllFlights() {
        return flightService.getAllFlights();
    }

    // POST /api/flights -> Registrar un vuelo
    @PostMapping
    public ResponseEntity<FlightDto> registerFlight(@RequestBody FlightDto flightDto) {
        FlightDto createdFlight = flightService.registerFlight(flightDto);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    // GET /api/flights/{id} -> Obtener detalles de un vuelo
    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable UUID id) {
        FlightDto flightDto = flightService.getFlightById(id);
        return ResponseEntity.ok(flightDto);
    }

    // GET /api/flights/suspicious -> Obtener vuelos "sospechosos"
    @GetMapping("/suspicious")
    public ResponseEntity<List<FlightDto>> getSuspiciousFlights() {
        List<FlightDto> suspiciousFlights = flightService.getSuspiciousFlights();
        return ResponseEntity.ok(suspiciousFlights);
    }

    // DELETE /api/flights/{id} -> Cancelar un vuelo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelFlight(@PathVariable UUID id) {
        flightService.cancelFlight(id);
        return ResponseEntity.noContent().build();
    }
}