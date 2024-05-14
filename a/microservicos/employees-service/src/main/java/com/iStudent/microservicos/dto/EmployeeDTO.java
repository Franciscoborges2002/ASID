package com.iStudent.microservicos.dto;

import com.iStudent.microservicos.dto.base.PersonEntityDTO;
import com.iStudent.microservicos.validation.ValidDepartment;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDTO extends PersonEntityDTO {

    @NotBlank
    private String jobTitle;

    @Positive
    @Min(6)
    @Max(8)
    private int workHours;

    @NotNull
    @ValidDepartment
    private DepartmentDTO department;
}
