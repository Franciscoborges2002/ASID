package com.iStudent.microservicos.studentmark.model.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iStudent.microservicos.studentmark.controller.TownCallerController;
import com.iStudent.microservicos.studentmark.dto.TownDTO;
import com.iStudent.microservicos.studentmark.model.Town;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidTownValidator implements ConstraintValidator<ValidTown, TownDTO> {

    private final TownCallerController townCallerController;

    @Override
    public boolean isValid(TownDTO town, ConstraintValidatorContext context) {
        String townResponse = townCallerController.findTownByName(town.getTownName());

        ObjectMapper objectMapper = new ObjectMapper();
        Town townCompare = null;
        try {
            townCompare = objectMapper.readValue(townResponse, Town.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return townCompare.getName().equals(town.getTownName());
    }
}
