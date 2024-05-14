package com.iStudent.microservicos.controller;

import com.iStudent.microservicos.dto.DepartmentDTO;
import com.iStudent.microservicos.dto.EmployeeDTO;
import com.iStudent.microservicos.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("department")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllEmployees() {
        return ResponseEntity.
                ok(departmentService.getAllDepartments());

    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable("id") Long departmentId) {

        Optional<DepartmentDTO> department = departmentService.getDepartmentById(departmentId);

        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody DepartmentDTO departmentDTO,
                                                   UriComponentsBuilder uriComponentsBuilder) {

        long newDepartmentId = departmentService.addDepartment(departmentDTO);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/department/{id}")
                        .build(newDepartmentId))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentDTO> deleteEmployeeById(@PathVariable("id") Long departmentId) {
        this.departmentService.deleteDepartmentById(departmentId);

        return ResponseEntity
                .noContent()
                .build();
    }

    /*@PutMapping("/edit/{id}")
    public ResponseEntity<DepartmentDTO> changeDepartment(@Valid @RequestBody DepartmentDTO departmentDTO,
                                                        @PathVariable("id") Long id) {

        EmployeeDTO employee = departmentService.changeDepartmentDetails(id, departmentDTO);

        if (employee == null) {
            return ResponseEntity
                    .notFound()
                    .build();

        } else {
            return ResponseEntity
                    .ok(employee);
        }
    }*/
}
