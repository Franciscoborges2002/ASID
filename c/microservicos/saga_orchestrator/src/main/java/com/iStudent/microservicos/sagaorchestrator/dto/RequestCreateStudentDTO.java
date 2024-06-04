package com.iStudent.microservicos.sagaorchestrator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class RequestCreateStudentDTO {

    private StudentDTO student;
    private ParentDTO parent;
}
