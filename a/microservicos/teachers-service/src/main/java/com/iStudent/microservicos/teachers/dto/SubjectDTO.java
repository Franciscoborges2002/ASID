package com.iStudent.microservicos.teachers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDTO {

    @NotBlank
    private String name;
}
