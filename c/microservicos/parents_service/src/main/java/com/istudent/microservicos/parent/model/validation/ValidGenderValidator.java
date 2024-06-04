package com.istudent.microservicos.parent.model.validation;

import com.istudent.microservicos.parent.model.enums.GenderEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ValidGenderValidator implements ConstraintValidator<ValidGender, GenderEnum> {

    private GenderEnum[] values;

    @Override
    public void initialize(ValidGender constraintAnnotation) {
        this.values = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(GenderEnum genderEnum, ConstraintValidatorContext context) {
        return Arrays.asList(values).contains(genderEnum);
    }
}