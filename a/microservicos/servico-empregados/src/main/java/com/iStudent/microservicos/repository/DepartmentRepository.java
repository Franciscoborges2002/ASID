package com.iStudent.microservicos.repository;

import com.iStudent.microservicos.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
