package com.elitewings_api.services;

import com.elitewings_api.dtos.CelebrityDto;
import com.elitewings_api.dtos.FlightDto;
import com.elitewings_api.dtos.JetDto;
import com.elitewings_api.entities.Celebrity;
import com.elitewings_api.entities.Jet;
import com.elitewings_api.entities.Flight;
import com.elitewings_api.exceptions.FlightNotFoundException;
import com.elitewings_api.repositories.CelebrityRepository;
import com.elitewings_api.repositories.FlightRepository;
import com.elitewings_api.repositories.JetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final CelebrityRepository celebrityRepository;
    private final JetRepository jetRepository;

    //Constructor
    public FlightService(FlightRepository flightRepository,
                         CelebrityRepository celebrityRepository,
                         JetRepository jetRepository) {
        this.flightRepository = flightRepository;
        this.celebrityRepository = celebrityRepository;
        this.jetRepository = jetRepository;
    }

    //Get All flights
    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    //POST
    public FlightDto registerFlight(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setId(UUID.randomUUID());

        // find the celebrity
        Celebrity celebrity = celebrityRepository.findByName(flightDto.getCelebrity().getName());
        if (celebrity == null) {
            throw new RuntimeException("Celebrity " + flightDto.getCelebrity().getName() + "not found.");
        }

        // find the jet
        Jet jet = jetRepository.findByModel(flightDto.getJet().getModel());
        if (jet == null) {
            throw new RuntimeException("Jet " + flightDto.getJet().getModel() + "not found.");
        }

        flight.setDepartureAirport(flightDto.getDepartureAirport());
        flight.setArrivalAirport(flightDto.getArrivalAirport());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setArrivalTime(flightDto.getArrivalTime());
        flight.setPurpose(flightDto.getPurpose());

        Flight savedFlight = flightRepository.save(flight);
        return convertToDto(savedFlight);
    }

    //GET (flight details)
    public FlightDto getFlightById(UUID id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found"));
        return convertToDto(flight);
    }

    //GET (flagged flights)
    public List<FlightDto> getSuspiciousFlights() {
        List<Flight> flights = flightRepository.findByPurpose("Suspicious");
        return flights.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // DELETE
    public void cancelFlight(UUID id) {
        if (!flightRepository.existsById(id)) {
            throw new FlightNotFoundException("Flight not found");
        }
        flightRepository.deleteById(id);
    }

    //convert to DTO
    private FlightDto convertToDto(Flight flight) {
        //Creamos una instancia de celebrityDto y agregamos los datos
        CelebrityDto celebrityDto = new CelebrityDto(
                flight.getCelebrity().getName(),
                flight.getCelebrity().getProfession(),
                flight.getCelebrity().getNetWorth(),
                flight.getCelebrity().getSuspiciousActivity()
        );

        String ownerId = flight.getJet().getOwner().getId().toString();

        JetDto jetDto = new JetDto(
                flight.getJet().getModel(),
                flight.getJet().getCapacity(),
                ownerId
        );

        return new FlightDto(
                celebrityDto,
                jetDto,
                flight.getDepartureAirport(),
                flight.getArrivalAirport(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getPurpose()
        );
    }
}
