package com.iStudent.microservicos.studentmark.model.validation;

import com.iStudent.microservicos.studentmark.model.enums.GenderEnum;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = ValidGenderValidator.class)
public @interface ValidGender {

    GenderEnum[] anyOf();

    String message() default "Invalid Gender";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
