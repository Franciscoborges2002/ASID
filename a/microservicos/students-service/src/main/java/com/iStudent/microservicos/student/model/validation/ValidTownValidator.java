package com.iStudent.microservicos.student.model.validation;

import com.iStudent.microservicos.student.dto.TownDTO;
import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import com.iStudent.microservicos.student.controller.TownCallerController;

@AllArgsConstructor
public class ValidTownValidator implements ConstraintValidator<ValidTown, TownDTO> {

    private final TownCallerController townCallerController; // go to the controller to talk to other service

    //Check if comes from town service and we have a value
    @Override
    public boolean isValid(TownDTO town, ConstraintValidatorContext context) {
        //ResponseEntity<TownDTO> returnService = townService.findByTownId(town.getId());

        // The response does not contain a body, handle accordingly
        //return returnService.hasBody();
        return true;
    }
}
