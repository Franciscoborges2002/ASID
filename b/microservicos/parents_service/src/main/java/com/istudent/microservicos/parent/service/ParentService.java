package com.istudent.microservicos.parent.service;

import com.istudent.microservicos.parent.dto.ParentDTO;
import com.istudent.microservicos.parent.model.Parent;
import com.istudent.microservicos.parent.model.Town;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

    /*public long addParent(ParentDTO parentDTO) {
        Town townToMap = townService.findByTownId(parentDTO.getTown().getId());

        Parent parent = mapper.map(parentDTO, Parent.class);
        parent.setTown(townToMap);

        parentRepository.save(parent);

        return parent.getId();

    }*/
}
