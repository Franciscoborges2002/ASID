package com.iStudent.microservicos.validation;

import com.iStudent.microservicos.dto.TownDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidTownValidator {
    private final TownRepository townRepository; // temos de ir ao servico

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
    }
}
