package com.istudent.microservicos.parent.dto.base;

import com.istudent.microservicos.parent.dto.TownDTO;
import com.istudent.microservicos.parent.model.enums.GenderEnum;
import com.istudent.microservicos.parent.model.validation.ValidGender;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@ToString
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
    //@ValidEGN
    private String EGN;

    @Positive
    @Min(14)
    private int age;

    @ValidGender(anyOf = {GenderEnum.M, GenderEnum.F})
    private GenderEnum genderEnum;

    @NotNull
    //@ValidTown
    private String town;

    @NotBlank
    @Email
    private String email;
}
