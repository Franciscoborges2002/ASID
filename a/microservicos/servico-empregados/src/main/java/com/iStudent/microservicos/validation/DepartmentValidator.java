package com.iStudent.microservicos.validation;

import com.iStudent.microservicos.dto.DepartmentDTO;
import com.iStudent.microservicos.repository.DepartmentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentValidator implements ConstraintValidator<ValidDepartment, DepartmentDTO> {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentValidator(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public boolean isValid(DepartmentDTO department, ConstraintValidatorContext context) {
        return departmentRepository.findById(department.getId()).isPresent();
    }
}
