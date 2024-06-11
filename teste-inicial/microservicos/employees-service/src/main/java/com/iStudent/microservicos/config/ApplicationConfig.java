package com.iStudent.microservicos.config;

import com.iStudent.microservicos.controller.TownCallerController;
import com.iStudent.microservicos.model.Department;
import com.iStudent.microservicos.model.Employee;
import com.iStudent.microservicos.model.enums.GenderEnum;
import com.iStudent.microservicos.repository.DepartmentRepository;
import com.iStudent.microservicos.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }

    //Load data into the bds
    @Bean
    CommandLineRunner initiateDepartment(DepartmentRepository repository) {
        return args -> {
            Department department = new Department();
            department.setId(1L);
            department.setName("Administration");
            repository.save(department);
        };
    }

    @Bean
    CommandLineRunner initiateEmployee(EmployeeRepository repository, DepartmentRepository departmentRepository) {
        return args -> {
            Employee employee = new Employee();

            //Set base person info
            employee.setFirstName("Francisco");
            employee.setMiddleName("Manuel");
            employee.setLastName("Borges");
            employee.setEGN("Nao sei");
            employee.setAge(21);
            employee.setGender(GenderEnum.M);
            employee.setEmail("a@a.com");

            //Set employee info
            employee.setJobTitle("Coordinator");
            employee.setWorkHours(8);
            employee.setDepartment(departmentRepository.findById(1L).get());
            repository.save(employee);
        };
    }
}