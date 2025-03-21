package com.elitewings_api.services;

import com.elitewings_api.dtos.JetDto;
import com.elitewings_api.entities.Jet;
import com.elitewings_api.exceptions.JetNotFoundException;
import com.elitewings_api.repositories.CelebrityRepository;
import com.elitewings_api.repositories.JetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class JetService {

    private final JetRepository jetRepository;
    private final CelebrityRepository celebrityRepository;

    @Autowired
    public JetService(JetRepository jetRepository, CelebrityRepository celebrityRepository) {
        this.jetRepository = jetRepository;
        this.celebrityRepository = celebrityRepository;
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
                .orElseThrow(() -> new JetNotFoundException("Jet " + id + " not found"));
        return convertToDto(jet);
    }
    // POST
    public JetDto createJet(JetDto jetDto) {
        Jet newJet = new Jet();
        newJet.setId(UUID.randomUUID());
        newJet.setModel(jetDto.getModel());
        newJet.setCapacity(jetDto.getCapacity());
        //Buscar la celebridad por id y agregarla
        newJet.setOwner(celebrityRepository.findCelebrityById(UUID.fromString(jetDto.getOwnerId())));
        //Guardar
        jetRepository.save(newJet);
        return convertToDto(newJet);
    };
    // Put
    public JetDto updateJet(UUID id,JetDto updateJet) {
        // Buscar id o rechazar si no existe
        Jet jet = jetRepository.findById(id)
                .orElseThrow(() -> new JetNotFoundException("Celebrity with id " + id + " not found"));

        //Asignar cambios necesarios
        if(updateJet.getModel() != null) {
            jet.setModel(updateJet.getModel());
        }
        if(updateJet.getCapacity() != null) {
            jet.setCapacity(updateJet.getCapacity());
        }
        if(updateJet.getOwnerId() != null) {
            jet.setOwner(celebrityRepository.findCelebrityById(UUID.fromString(updateJet.getOwnerId())));
        }

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
        return new JetDto(
                jet.getModel(),
                jet.getCapacity(),
                jet.getOwner().getName()
        );
    }
}

