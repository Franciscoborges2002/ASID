package com.iStudent.microservicos.town_country.service;

import com.iStudent.microservicos.town_country.dto.ClubDTO;
import com.iStudent.microservicos.town_country.dto.CountryDTO;
import com.iStudent.microservicos.town_country.dto.ReturnClubTownDTO;
import com.iStudent.microservicos.town_country.dto.TownDTO;
import com.iStudent.microservicos.town_country.extern.ClubCallerService;
import com.iStudent.microservicos.town_country.model.Club;
import com.iStudent.microservicos.town_country.model.Country;
import com.iStudent.microservicos.town_country.model.Town;
import com.iStudent.microservicos.town_country.model.enums.OperationStatusEnum;
import com.iStudent.microservicos.town_country.repository.TownRepository;
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

    private final ClubCallerService clubCallerService;

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

    private CountryDTO mapToCountryDTO(Country country) {
        return mapper.map(country, CountryDTO.class);
    }

    public List<TownDTO> convertTownsToListOfDTOs(List<Town> towns) {
        List<TownDTO> townDTOs = new ArrayList<>();

        for (Town town : towns) {
            TownDTO townDTO = new TownDTO();
            // Map fields from town to townDTO
            townDTO.setId(town.getId());
            townDTO.setName(town.getName());
            townDTO.setCountry(mapToCountryDTO(town.getCountry()));
            townDTO.setNumberCitizens(town.getNumberCitizens());
            // Add other mappings as needed
            townDTOs.add(townDTO);
        }
        return townDTOs;
    }

    public ReturnClubTownDTO attachClubToTown(Long clubId, Long townId){
        ReturnClubTownDTO returnClubTownDTO = new ReturnClubTownDTO();

        Town town = new Town();
        ClubDTO clubVerification = new ClubDTO();

        boolean clubExists = false;
        boolean townExists = false;

        //Try get the club
        try{
            Optional<Town> optClub = townRepository.findById(townId);

            //If club exists
            if(!optClub.isEmpty()){
                town = optClub.get();
                townExists = true;
            }

        }catch(Exception e){
            System.out.println(e);
            returnClubTownDTO.setOperationStatus(OperationStatusEnum.ERROR);
            returnClubTownDTO.setReason("Cant find Town!");

            return returnClubTownDTO;
        }

        //Verify if town exists
        try{
            clubVerification = clubCallerService.findClubById(clubId);

            //If town exists
            if(clubVerification.getId() != null){
                clubExists = true;
            }

        }catch(Exception e){
            System.out.println(e);
            returnClubTownDTO.setOperationStatus(OperationStatusEnum.ERROR);
            returnClubTownDTO.setReason("Club doesnt exist!");

            return returnClubTownDTO;
        }

        try{
            Club clubAdd = new Club();
            clubAdd.setId(townId);
            clubAdd.setTown(town);

            town.setClub(clubAdd);

            System.out.println("testeee");
            System.out.println(town.getClub().getId());

            townRepository.save(town);

        }catch(Exception e){
            System.out.println(e);
            returnClubTownDTO.setOperationStatus(OperationStatusEnum.FAILURE);
            returnClubTownDTO.setReason("Cant associate club with town. Club service!");

            return returnClubTownDTO;
        }

        returnClubTownDTO.setOperationStatus(OperationStatusEnum.SUCCESS);
        returnClubTownDTO.setReason("Club and Town associated! Town service!");
        returnClubTownDTO.setClubDTO(clubVerification);
        returnClubTownDTO.setTownDTO(mapToTownDTO(town));

        return returnClubTownDTO;
    }
}
