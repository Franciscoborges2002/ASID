package com.example.microservicos.dto;

import com.example.microservicos.dto.base.PersonEntityDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentDTO extends PersonEntityDTO {

    private final LocalDate enrollDate = LocalDate.now();

    //private ParentDTO parent;
}