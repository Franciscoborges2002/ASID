package com.iStudent.microservicos.service;

import com.iStudent.microservicos.dto.DepartmentDTO;
import com.iStudent.microservicos.dto.EmployeeDTO;
import com.iStudent.microservicos.dto.TownDTO;
import com.iStudent.microservicos.model.Department;
import com.iStudent.microservicos.model.Employee;
import com.iStudent.microservicos.model.Town;
import com.iStudent.microservicos.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final ModelMapper mapper;

    private DepartmentDTO mapToDepartmentDTO(Department department) {
        return mapper.map(department, DepartmentDTO.class);
    }

    public Department findDepartmentById(Long departmentID) {
        return departmentRepository.findById(departmentID).orElseThrow();
    }

    public Optional<DepartmentDTO> getDepartmentById(Long id) {
        return departmentRepository.
                findById(id).
                map(this::mapToDepartmentDTO);
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.
                findAll().
                stream().
                map(this::mapToDepartmentDTO).
                toList();
    }

    public long addDepartment(DepartmentDTO departmentDTO) {
        Department department = mapper.map(departmentDTO, Department.class);

        departmentRepository.save(department);

        return department.getId();
    }

    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    /*public DepartmentDTO changeDepartmentDetails(Long departmentId, DepartmentDTO departmentDTO) {
        Department newDepartment = departmentService.findDepartmentById(employeeDTO.getDepartment().getId());
        Optional<Employee> optionalEmployee = depart.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setJobTitle(employeeDTO.getJobTitle());
            employee.setWorkHours(employeeDTO.getWorkHours());
            employee.setDepartment(newDepartment);
            employeeRepository.save(employee);
            return mapToEmployeeDTO(employee);
        }

        return null;
    }*/
}
