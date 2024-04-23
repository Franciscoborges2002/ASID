package com.iStudent.microservicos.dto.base;

import com.iStudent.microservicos.dto.TownDTO;
import com.iStudent.microservicos.model.enums.GenderEnum;
import com.iStudent.microservicos.validation.ValidEGN;
import com.iStudent.microservicos.validation.ValidGender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
