package com.iStudent.microservicos.teachers.model.validation;

import com.iStudent.microservicos.teachers.dto.TownDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class ValidTownValidator implements ConstraintValidator<ValidTown, TownDTO> {

    /*private final TownRepository townRepository;

    @Autowired
    public ValidTownValidator(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public boolean isValid(TownDTO town, ConstraintValidatorContext context) {
        return
                townRepository
                        .findById(town.getId())
                        .isPresent();
    }*/
}
