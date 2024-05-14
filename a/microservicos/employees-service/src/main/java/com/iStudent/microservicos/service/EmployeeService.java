package com.iStudent.microservicos.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iStudent.microservicos.controller.TownCallerController;
import com.iStudent.microservicos.dto.EmployeeDTO;
import com.iStudent.microservicos.dto.TownDTO;
import com.iStudent.microservicos.model.Department;
import com.iStudent.microservicos.model.Employee;
import com.iStudent.microservicos.model.Town;
import com.iStudent.microservicos.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final TownCallerController townCallerController;

    private final DepartmentService departmentService;

    private final ModelMapper mapper;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.
                findAll().
                stream().
                map(this::mapToEmployeeDTO).
                toList();
    }

    private EmployeeDTO mapToEmployeeDTO(Employee employee) {
        return mapper.map(employee, EmployeeDTO.class);
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.
                findById(id).
                map(this::mapToEmployeeDTO);
    }

    public long addEmployee(EmployeeDTO employeeDTO) throws JsonProcessingException {
        //Get the town from the service
        String townFromMicroService = townCallerController.findTownByName(employeeDTO.getTown().getName());

        //Pass from string to town object
        ObjectMapper objectMapper = new ObjectMapper();
        Town town = objectMapper.readValue(townFromMicroService, Town.class);

        /*ResponseEntity<TownDTO> responseTown = townService.findByTownId(employeeDTO.getTown().getId());
        Town townToMap = convertDtoToEntity(responseTown);

*/
        Department departmentToMap = departmentService.findDepartmentById(employeeDTO.getDepartment().getId());

        Employee employee = mapper.map(employeeDTO, Employee.class);
        employee.setTownId(town.getId());
        employee.setTownName(town.getName());
        employee.setDepartment(departmentToMap);

        employeeRepository.save(employee);

        return employee.getId();
    }

    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public EmployeeDTO changeEmployeeDetails(Long employeeId, EmployeeDTO employeeDTO) {
        Department newDepartment = departmentService.findDepartmentById(employeeDTO.getDepartment().getId());
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setJobTitle(employeeDTO.getJobTitle());
            employee.setWorkHours(employeeDTO.getWorkHours());
            employee.setDepartment(newDepartment);
            employeeRepository.save(employee);
            return mapToEmployeeDTO(employee);
        }

        return null;
    }

    public Town convertDtoToEntity(ResponseEntity<TownDTO> responseEntity) {
        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.hasBody()) {
            TownDTO townDTO = responseEntity.getBody();
            return mapper.map(townDTO, Town.class);
        }
        return null; // or handle the case when the response is not successful or does not contain a body
    }
}
