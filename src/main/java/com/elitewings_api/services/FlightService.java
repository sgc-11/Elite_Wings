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
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    public FlightService(FlightRepository flightRepository,
                         CelebrityRepository celebrityRepository,
                         JetRepository jetRepository) {
        this.flightRepository = flightRepository;
        this.celebrityRepository = celebrityRepository;
        this.jetRepository = jetRepository;
    }

    //Get All
    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    //GET (flight details)
    public FlightDto getFlightById(UUID id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight" + id +"not found"));
        return convertToDto(flight);
    }

    //POST
    public FlightDto registerFlight(FlightDto flightDto) {
        Flight newFlight = new Flight();
        newFlight.setId(UUID.randomUUID());
        newFlight.setArrivalAirport(flightDto.getArrivalAirport());
        newFlight.setDepartureAirport(flightDto.getDepartureAirport());
        //Celebridad
        newFlight.setCelebrity(celebrityRepository.findCelebrityById(UUID.fromString(flightDto.getCelebrity())));
        //Jet
        newFlight.setJet(jetRepository.findJetById(UUID.fromString(flightDto.getJet())));
        //Arrival y departure time
        newFlight.setDepartureTime(flightDto.getDepartureTime());
        newFlight.setArrivalTime(flightDto.getArrivalTime());

        //Save
        flightRepository.save(newFlight);
        return convertToDto(newFlight);
    }

    //GET (flagged flights)
    public List<FlightDto> getSuspiciousFlights() {
        List<Flight> flights = flightRepository.findByPurpose("Suspicious");
        return flights.stream()
                .map(this::convertToDto)
                .toList();
    }

    // DELETE
    public void cancelFlight(UUID id) {
        if (!flightRepository.existsById(id)) {
            throw new FlightNotFoundException("Flight "+ id + "not found");
        }
        flightRepository.deleteById(id);
    }

    //convert to DTO
    private FlightDto convertToDto(Flight flight) {

        return new FlightDto(
                flight.getCelebrity().getName(),
                flight.getJet().getModel(),
                flight.getDepartureAirport(),
                flight.getArrivalAirport(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getPurpose()
        );
    }
}
