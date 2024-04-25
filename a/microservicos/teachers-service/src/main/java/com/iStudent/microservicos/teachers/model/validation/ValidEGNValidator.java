package com.iStudent.microservicos.teachers.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidEGNValidator implements ConstraintValidator<ValidEGN, String> {


    @Override
    public boolean isValid(String EGN, ConstraintValidatorContext context) {
        return EGN.length() == 10;
    }
}