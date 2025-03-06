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
        Celebrity celebrity = new Celebrity();
        celebrity.setId(UUID.randomUUID());
        celebrity.setName(celebrityDto.getName());
        celebrity.setProfession(celebrityDto.getProfession());
        celebrity.setNetWorth(celebrityDto.getNetWorth());
        celebrity.setSuspiciousActivity(celebrityDto.getSuspiciousActivity());
        celebrityRepository.save(celebrity);
        return convertToDto(celebrity);
    };
// Put
    public CelebrityDto updateCelebrity(UUID id,CelebrityDto celebrityDto) {
        Celebrity celebrity = celebrityRepository.findById(id)
                .orElseThrow(() -> new CelebrityNotFoundException("Celebrity with id " + id + " not found"));
        celebrity.setName(celebrityDto.getName());
        celebrity.setProfession(celebrityDto.getProfession());
        celebrity.setNetWorth(celebrityDto.getNetWorth());
        celebrity.setSuspiciousActivity(celebrityDto.getSuspiciousActivity());
        celebrityRepository.save(celebrity);
        return convertToDto(celebrity);
    };
// Delete
    public void deleteCelebrity(UUID id) {
        Celebrity celebrity = celebrityRepository.findById(id)
                .orElseThrow(() -> new CelebrityNotFoundException("Celebrity with id " + id + " not found"));
        celebrityRepository.deleteById(id);
    }



    private CelebrityDto convertToDto(Celebrity celebrity) {
        return new CelebrityDto(
                celebrity.getName(),
                celebrity.getProfession(),
                celebrity.getNetWorth(),
                celebrity.getSuspiciousActivity()
        );
    }
}
