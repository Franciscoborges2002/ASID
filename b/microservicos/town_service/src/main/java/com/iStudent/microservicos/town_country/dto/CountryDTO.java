package com.iStudent.microservicos.town_country.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDTO {
    private Long id;

    private String name;

    private int numberCitizens;
}
