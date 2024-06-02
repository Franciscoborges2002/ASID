package com.iStudent.microservicos.town.service;

import com.iStudent.microservicos.town.dto.TownDTO;
import com.iStudent.microservicos.town.model.Town;
import com.iStudent.microservicos.town.repository.TownRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TownService {

    private final TownRepository townRepository;
    private final ModelMapper mapper;

    public List<TownDTO> getTowns(){
        return convertTownsToListOfDTOs(this.townRepository.findAll());
    }

    public Optional<TownDTO> findByTownId(Long townId){
        return this.townRepository.findById(townId)
                .map(this::mapToTownDTO);
    }

    public Optional<TownDTO> findByTownName(String townName){
        return this.townRepository.findByName(townName)
                .map(this::mapToTownDTO);
    }

    private TownDTO mapToTownDTO(Town town) {
        return mapper.map(town, TownDTO.class);
    }

    public List<TownDTO> convertTownsToListOfDTOs(List<Town> towns) {
        List<TownDTO> townDTOs = new ArrayList<>();
        for (Town town : towns) {
            TownDTO townDTO = new TownDTO();
            // Map fields from town to townDTO
            townDTO.setId(town.getId());
            townDTO.setName(town.getName());
            // Add other mappings as needed
            townDTOs.add(townDTO);
        }
        return townDTOs;
    }
}
