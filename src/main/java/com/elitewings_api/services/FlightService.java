package com.elitewings_api.services;

import com.elitewings_api.dtos.CelebrityDto;
import com.elitewings_api.dtos.FlightDto;
import com.elitewings_api.dtos.JetDto;
import com.elitewings_api.entities.Celebrity;
import com.elitewings_api.entities.Flight;
import com.elitewings_api.repositories.CelebrityRepository;
import com.elitewings_api.repositories.FlightRepository;
import com.elitewings_api.repositories.JetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //convert to DTO
    private FlightDto convertToDto(Flight flight) {
        //Creamos una instancia de celebrityDto y agregamos los datos
        CelebrityDto celebrityDto = new CelebrityDto(
                flight.getCelebrity().getName(),
                flight.getCelebrity().getProfession(),
                flight.getCelebrity().getNetWorth(),
                flight.getCelebrity().getSuspiciousActivity()
        );
        //Para el owner del jet
        CelebrityDto ownerDto = new CelebrityDto(
                flight.getJet().getOwner().getName(),
                flight.getJet().getOwner().getProfession(),
                flight.getJet().getOwner().getNetWorth(),
                flight.getJet().getOwner().getSuspiciousActivity()
        );

        JetDto jetDto = new JetDto(
                flight.getJet().getModel(),
                flight.getJet().getCapacity(),
                ownerDto
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

    // Helper method to find Celebrity entity from CelebrityDto
//    private Celebrity findCelebrityByDto(CelebrityDto celebrityDto) {
//        return celebrityRepository.findByName(celebrityDto.getName())
//                .orElseThrow(() -> new RuntimeException("Celebrity not found: " + celebrityDto.getName()));
//    }
//
//    // Helper method to find Jet entity from JetDto
//    private Jet findJetByDto(JetDto jetDto) {
//        return jetRepository.findByModel(jetDto.getModel())
//                .orElseThrow(() -> new RuntimeException("Jet not found: " + jetDto.getModel()));
//    }


}
