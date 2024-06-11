package com.iStudent.microservicos.sagaorchestrator.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TownDTO {

    private Long id;

    @NotBlank
    private String name;

    private CountryDTO country;

    private int numberCitizens;
}
