package com.example.microservicos.dto;

import com.example.microservicos.model.validation.UniqueClubName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubDTO {

    @NotBlank
    @Size(min = 2, max = 20)
    @UniqueClubName
    private String name;

    @NotBlank
    @Size(min = 5, max = 500)
    private String description;
}
