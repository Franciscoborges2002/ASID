package com.iStudent.microservicos.studentmark.model.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iStudent.microservicos.studentmark.extern.TownCallerController;
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
        TownDTO townResponse = townCallerController.findTownByName(town.getName());

        System.out.println(townResponse);

        System.out.println(townResponse.getName());

        System.out.println(town.getName());

        return townResponse.getName().equals(town.getName());
    }
}
