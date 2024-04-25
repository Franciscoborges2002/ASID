package com.iStudent.microservicos.student.dto;

import com.iStudent.microservicos.student.dto.base.PersonEntityDTO;

import java.time.LocalDate;

public class StudentDTO extends PersonEntityDTO {

    private final LocalDate enrollDate = LocalDate.now();

    private ParentDTO parent;
}
