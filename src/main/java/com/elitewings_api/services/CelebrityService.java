package com.elitewings_api.services;

import com.elitewings_api.dtos.CelebrityDto;
import com.elitewings_api.entities.Celebrity;
import com.elitewings_api.exceptions.CelebrityNotFoundException;
import com.elitewings_api.repositories.CelebrityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CelebrityService {
    private final CelebrityRepository celebrityRepository;

    public CelebrityService(CelebrityRepository celebrityRepository) {
        this.celebrityRepository = celebrityRepository;
    }
//Get All
    public List<CelebrityDto> getAllCelebrities() {
        return celebrityRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }
// GET one celebrity by ID
    public CelebrityDto getCelebrityById(UUID id) {
        Celebrity celebrity = celebrityRepository.findById(id)
             .orElseThrow(() -> new CelebrityNotFoundException("Celebrity not found"));
        return convertToDto(celebrity);
}
// Post
    public CelebrityDto createCelebrity(CelebrityDto celebrityDto) {
        Celebrity newCeb = new Celebrity();
        newCeb.setId(UUID.randomUUID()); // Asigna UUID
        //Asigna valores ingresados por JSON
        newCeb.setName(celebrityDto.getName());
        newCeb.setProfession(celebrityDto.getProfession());
        newCeb.setNetWorth(celebrityDto.getNetWorth());
        newCeb.setSuspiciousActivity(celebrityDto.getSuspiciousActivity());
        //Guarda y retorna
        celebrityRepository.save(newCeb);
        return convertToDto(newCeb);
    };
// Put
    public CelebrityDto updateCelebrity(UUID id,CelebrityDto celebrityDto) {
        //Buscar el id
        Celebrity updatedCeb = celebrityRepository.findById(id)
                .orElseThrow(() -> new CelebrityNotFoundException("Celebrity id " + id + " not found"));

        //Asigna el cambio o los cambios que sean necesarios si no son entradas nulas
        if(celebrityDto.getName() != null) {
            updatedCeb.setName(celebrityDto.getName());
        }
        if(celebrityDto.getProfession() != null) {
            updatedCeb.setProfession(celebrityDto.getProfession());
        }
        if(celebrityDto.getNetWorth() != null) {
            updatedCeb.setNetWorth(celebrityDto.getNetWorth());
        }
        if(celebrityDto.getSuspiciousActivity() != null) {
            updatedCeb.setSuspiciousActivity(celebrityDto.getSuspiciousActivity());
        }

        //Guardar
        celebrityRepository.save(updatedCeb);
        return convertToDto(updatedCeb);
    };
// Delete
    public void deleteCelebrity(UUID id) {
        //Buscar por id
        Celebrity celebrity = celebrityRepository.findById(id)
                .orElseThrow(() -> new CelebrityNotFoundException("Celebrity with id " + id + " not found"));

        celebrityRepository.deleteById(id);
    }


//Metodo para seleccionar los datos para el DTO
    private CelebrityDto convertToDto(Celebrity celebrity) {
        return new CelebrityDto(
                celebrity.getName(),
                celebrity.getProfession(),
                celebrity.getNetWorth(),
                celebrity.getSuspiciousActivity()
        );
    }
}
