package com.iStudent.microservicos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidEGNValidator implements ConstraintValidator<ValidEGN, String> {

    @Override
    public boolean isValid(String EGN, ConstraintValidatorContext context) {
        System.out.println("EGNNNN " + EGN);

        //If its not null
        if(EGN != null){
            return EGN.length() == 10;
        }

        return false;
    }
}
