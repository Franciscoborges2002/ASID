package com.iStudent.microservicos.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iStudent.microservicos.dto.EmployeeDTO;
import com.iStudent.microservicos.dto.TownDTO;
import com.iStudent.microservicos.service.EmployeeService;
import com.netflix.discovery.EurekaClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.
                ok(employeeService.getAllEmployees());

    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long employeeId) {

        Optional<EmployeeDTO> employee = employeeService.getEmployeeById(employeeId);

        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO,
                                                   UriComponentsBuilder uriComponentsBuilder) throws JsonProcessingException {
        long newEmployeeId = employeeService.addEmployee(employeeDTO);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/employees/{id}")
                        .build(newEmployeeId))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployeeById(@PathVariable("id") Long employeeId) {
        this.employeeService.deleteEmployeeById(employeeId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<EmployeeDTO> changeDepartment(@Valid @RequestBody EmployeeDTO employeeDTO,
                                                        @PathVariable("id") Long id) {

        EmployeeDTO employee = this.employeeService.changeEmployeeDetails(id, employeeDTO);

        if (employee == null) {
            return ResponseEntity
                    .notFound()
                    .build();

        } else {
            return ResponseEntity
                    .ok(employee);
        }
    }
}
