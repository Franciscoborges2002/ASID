package com.iStudent.microservicos.validation;

import jakarta.validation.*;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidEGNValidator.class)
public @interface ValidEGN {

    String message() default "Invalid EGN";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
