package com.iStudent.microservicos.sagaorchestrator.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestCreateStudentDTO {

    StudentDTO student;
    ParentDTO parent;
}
