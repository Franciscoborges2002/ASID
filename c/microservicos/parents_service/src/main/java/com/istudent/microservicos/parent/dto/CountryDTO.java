package com.istudent.microservicos.parent.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CountryDTO {
    private Long id;

    @NotBlank
    private String name;
}
