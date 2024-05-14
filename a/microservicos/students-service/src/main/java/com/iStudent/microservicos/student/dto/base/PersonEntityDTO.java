package com.iStudent.microservicos.student.dto.base;

import com.iStudent.microservicos.student.dto.TownDTO;
import com.iStudent.microservicos.student.model.enums.GenderEnum;
import com.iStudent.microservicos.student.model.validation.ValidEGN;
import com.iStudent.microservicos.student.model.validation.ValidGender;
import com.iStudent.microservicos.student.model.validation.ValidTown;
import jakarta.validation.constraints.*;

public abstract class PersonEntityDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String middleName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;

    @NotBlank
    @ValidEGN
    private String EGN;

    @Positive
    @Min(14)
    private int age;

    @ValidGender(anyOf = {GenderEnum.M, GenderEnum.F})
    private GenderEnum genderEnum;

    @NotNull
    @ValidTown
    private TownDTO town;

    @NotBlank
    @Email
    private String email;
}
