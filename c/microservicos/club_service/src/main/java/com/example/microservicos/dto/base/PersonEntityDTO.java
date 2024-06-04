package com.example.microservicos.dto.base;

import com.example.microservicos.model.enums.GenderEnum;
import com.example.microservicos.model.validation.ValidGender;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class PersonEntityDTO {

    //private Long id;

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
    //@ValidEGN
    private String EGN;

    @Positive
    @Min(14)
    private int age;

    @ValidGender(anyOf = {GenderEnum.M, GenderEnum.F})
    private GenderEnum genderEnum;

    @NotBlank
    //@ValidTown
    @Size(min=2, max=300)
    private String town;

    @NotBlank
    @Email
    private String email;
}
