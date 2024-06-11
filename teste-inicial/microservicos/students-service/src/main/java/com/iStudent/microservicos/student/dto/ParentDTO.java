package com.iStudent.microservicos.student.dto;


import com.iStudent.microservicos.student.dto.base.PersonEntityDTO;
import com.iStudent.microservicos.student.model.validation.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParentDTO {

    private Long id;

    private String name;
}
