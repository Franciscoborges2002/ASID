package com.iStudent.microservicos.studentmark.model.validation;

import com.iStudent.microservicos.studentmark.extern.TownCallerService;
import com.iStudent.microservicos.studentmark.dto.TownDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidTownValidator implements ConstraintValidator<ValidTown, TownDTO> {

    private final TownCallerService townCallerController;

    @Override
    public boolean isValid(TownDTO town, ConstraintValidatorContext context) {
        TownDTO townResponse = townCallerController.findTownByName(town.getName());

        System.out.println(townResponse);

        System.out.println(townResponse.getName());

        System.out.println(town.getName());

        return townResponse.getName().equals(town.getName());
    }
}
