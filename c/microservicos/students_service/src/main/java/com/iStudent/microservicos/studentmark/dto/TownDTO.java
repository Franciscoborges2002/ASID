package com.iStudent.microservicos.studentmark.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TownDTO {
    private Long id;

    @NotBlank
    private String name;

    private CountryDTO country;
}
