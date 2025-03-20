package com.elitewings_api.services;

import com.elitewings_api.dtos.AirportDto;
import com.elitewings_api.entities.Airport;
import com.elitewings_api.exceptions.AirportNotFoundException;
import com.elitewings_api.repositories.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    // GET all airports
    public List<AirportDto> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // GET airport by ID
    public AirportDto getAirportById(UUID id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException("Airport not found"));
        return convertToDto(airport);
    }

    private AirportDto convertToDto(Airport airport) {
        AirportDto dto = new AirportDto();
        dto.setName(airport.getName());
        dto.setCapacity(airport.getCapacity());
        return dto;
    }
}