package com.istudent.microservicos.parent.service;

import com.istudent.microservicos.parent.dto.DeleteDTO;
import com.istudent.microservicos.parent.dto.ParentDTO;
import com.istudent.microservicos.parent.dto.TownDTO;
import com.istudent.microservicos.parent.extern.TownCallerService;
import com.istudent.microservicos.parent.model.Parent;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.istudent.microservicos.parent.repository.ParentRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;

    private final ModelMapper mapper;

    private final TownCallerService towncallerService;

    public Parent findParentById(Long parentId) {
        return parentRepository.findById(parentId).orElse(null);
    }

    public List<ParentDTO> getAllParents() {
        return parentRepository.
                findAll().
                stream().
                map(this::mapToParentDTO).
                toList();
    }

    private ParentDTO mapToParentDTO(Parent parent) {
        return mapper.map(parent, ParentDTO.class);
    }

    public Optional<ParentDTO> getParentById(Long parentId) {
        return parentRepository.
                findById(parentId).
                map(this::mapToParentDTO);
    }

    public ParentDTO addParent(ParentDTO parentDTO) {
        Parent parent = new Parent();
        ParentDTO parentDTOtoReturn = new ParentDTO();

        try{
            TownDTO townResponse = towncallerService.findTownByName(parentDTO.getTown());

            //If exists the town in the town microservice
            if(townResponse.getName().equals(parentDTO.getTown())){

                try{
                    parent = mapper.map(parentDTO, Parent.class);

                    //Set town to student
                    parent.setTown(townResponse.getName());

                    //Save to repos
                    parentRepository.save(parent);

                    parentDTOtoReturn = mapper.map(parent, ParentDTO.class);

                }catch(Exception e){
                    throw new IllegalArgumentException("Error while creating parent!");
                }

                return parentDTOtoReturn;
            }

        }catch(Exception ex){
            throw new IllegalArgumentException("The town you inserted doesnt exist");
        }

        throw new IllegalArgumentException("Couldn't add parent!");
    }

    public DeleteDTO deleteParentById(Long parentId) {
        DeleteDTO response = new DeleteDTO();
        Optional<Parent> parent = parentRepository.findById(parentId);

        if(parent.isPresent()){
            parentRepository.deleteById(parentId);

            response.setId(parentId);
            response.setMessage("Successfully deleted parent!");

            return response;
        }

        response.setMessage("Parent was not deleted!");

        return response;
    }
}
