package com.iStudent.microservicos.teachers.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TownDTO {

    private Long id;

    @NotBlank
    private String name;

    private CountryDTO country;

}
