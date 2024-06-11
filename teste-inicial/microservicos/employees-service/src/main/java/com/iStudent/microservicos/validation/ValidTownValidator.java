package com.iStudent.microservicos.validation;

import com.iStudent.microservicos.controller.TownCallerController;
import com.iStudent.microservicos.dto.TownDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidTownValidator implements ConstraintValidator<ValidTown, TownDTO> {
    private final TownCallerController townCallerController; // temos de ir ao servico

    @Override
    public boolean isValid(TownDTO town, ConstraintValidatorContext context) {
        //ResponseEntity<TownDTO> returnService = townService.findByTownId(town.getId());

        // The response does not contain a body, handle accordingly
        //return returnService.hasBody();
        return true;
    }
}
