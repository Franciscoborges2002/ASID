package com.example.microservicos.dto;

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

    private int numberCitizens;
}
