package com.elitewings_api.services;

import com.elitewings_api.dtos.CelebrityDto;
import com.elitewings_api.dtos.JetDto;
import com.elitewings_api.entities.Celebrity;
import com.elitewings_api.entities.Jet;
import com.elitewings_api.exceptions.JetNotFoundException;
import com.elitewings_api.repositories.JetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class JetService {
    private final JetRepository jetRepository;

    public JetService(JetRepository jetRepository) {
        this.jetRepository = jetRepository;
    }
    //Get All
    public List<JetDto> getAllJets() {
        return jetRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }
    // GET one jet by ID
    public JetDto getJetById(UUID id) {
        Jet jet = jetRepository.findById(id)
                .orElseThrow(() -> new JetNotFoundException("Celebrity not found"));
        return convertToDto(jet);
    }
    // Post
    public JetDto AddJet(JetDto jetDto) {
        Jet jet = new Jet();
        jet.setModel(jetDto.getModel());
        jet.setCapacity(jetDto.getCapacity());
        jet.setOwner(jet.getOwner());
        jetRepository.save(jet);
        return convertToDto(jet);
    };
    // Put
    public JetDto updateJet(UUID id,JetDto celebrityDto) {
        Jet jet = jetRepository.findById(id)
                .orElseThrow(() -> new JetNotFoundException("Celebrity with id " + id + " not found"));
        jet.setModel(celebrityDto.getModel());
        jet.setCapacity(celebrityDto.getCapacity());
        jet.setOwner(jet.getOwner());
        jetRepository.save(jet);
        return convertToDto(jet);
    };
    // Delete
    public void deleteJet(UUID id) {
        Jet jet = jetRepository.findById(id)
                .orElseThrow(() -> new JetNotFoundException("Jet with id " + id + " not found"));
        jetRepository.deleteById(id);
    }



    private JetDto convertToDto(Jet jet) {

        Celebrity owner = jet.getOwner();
        CelebrityDto ownerDto = new CelebrityDto(
                owner.getName(),
                owner.getProfession(),
                owner.getNetWorth(),
                owner.getSuspiciousActivity()
        );

        return new JetDto(
                jet.getModel(),
                jet.getCapacity(),
                ownerDto
        );
    }
}

