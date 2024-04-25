package com.iStudent.microservicos.student.repository;

import com.iStudent.microservicos.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    Optional<List<Student>> findByParentId(Long parentId);


    //Optional<List<Student>> findByClubs(Club club);
}
