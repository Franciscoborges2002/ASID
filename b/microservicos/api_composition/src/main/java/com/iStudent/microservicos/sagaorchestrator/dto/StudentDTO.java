package com.iStudent.microservicos.sagaorchestrator.dto;

import com.iStudent.microservicos.sagaorchestrator.dto.base.PersonEntityDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class StudentDTO extends PersonEntityDTO {

    private final LocalDate enrollDate = LocalDate.now();

}
