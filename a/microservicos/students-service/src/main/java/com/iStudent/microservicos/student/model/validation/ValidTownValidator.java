package com.iStudent.microservicos.student.model.validation;

import com.iStudent.microservicos.student.dto.TownDTO;
import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidTownValidator implements ConstraintValidator<ValidTown, TownDTO> {

    /*private final TownRepository townRepository;

    @Override
    public boolean isValid(TownDTO town, ConstraintValidatorContext context) {
        return
                townRepository
                        .findById(town.getId())
                        .isPresent();
    }*/
}
